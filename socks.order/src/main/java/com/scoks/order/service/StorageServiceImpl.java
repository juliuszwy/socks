package com.scoks.order.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.dto.MaterialSumDTO;
import com.scoks.order.entity.*;
import com.scoks.order.exception.ResultException;
import com.scoks.order.exception.ResultStatus;
import com.scoks.order.mapper.MaterialMapper;
import com.scoks.order.mapper.StorageLogMapper;
import com.scoks.order.mapper.StorageRegionMapper;
import com.scoks.order.mapper.StorageTrayMaterialMapper;
import com.scoks.order.query.MaterialQuery;
import com.scoks.order.query.StorageLogQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class StorageServiceImpl {
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private StorageLogMapper storageLogMapper;
    @Resource
    private StorageTrayMaterialMapper storageTrayMaterialMapper;
    @Resource
    private StorageRegionMapper storageRegionMapper;

    public Page<StorageRegion> findStorageRegionPageList(Page<StorageRegion> page, StorageRegion form) {
        List<StorageRegion> storageRegions = storageRegionMapper.findStorageRegionPageList(page, form);
        page.setRecords(storageRegions);
        return page;
    }

    public void setStorageRegion(StorageRegion form) {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            storageRegionMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            storageRegionMapper.updateById(form);
        }
    }


    public Page<Material> findMaterialPageList(Page<Material> page, Material form) {
        List<Material> materials = materialMapper.listMaterials(page, form);
        page.setRecords(materials);
        return page;
    }

    public Material saveIfAbsent(Material form) {
        Wrapper<Material> queryWrapper = new QueryWrapper<>(form);
        Material material = materialMapper.selectOne(queryWrapper);
        if(material == null){
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            form.setState(0);
            materialMapper.insert(form);
            return form;
        }else{
            return material;
        }
    }

    public Long setMaterial(Material form) {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            form.setState(0);
            materialMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            materialMapper.updateById(form);
        }
        return form.getId();
    }


    public Page<StorageTrayMaterial> findStorageTrayMaterialPageList(Page<StorageTrayMaterial> page, MaterialQuery form) {
        List<StorageTrayMaterial> materials = storageTrayMaterialMapper.storageTrayMaterials(page, form);
        page.setRecords(materials);
        return page;
    }

    public Page<MaterialSumDTO> statisticsStorageMaterialPageList(Page<MaterialSumDTO> page, MaterialQuery form) {
        List<MaterialSumDTO> materialSumDTOS = storageTrayMaterialMapper.statisticsStorageMaterialPageList(page, form);
        page.setRecords(materialSumDTOS);
        return page;
    }

    public Page<StorageLog> findStorageLogPageList(Page<StorageLog> page, StorageLogQuery form) {
        List<StorageLog> materials = storageLogMapper.listStorageLog(page, form);
        page.setRecords(materials);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insetStorageLog(StorageLog form) {
        long now = System.currentTimeMillis();
        String tray = form.getTray();
        Long materialId = form.getMaterialId();
        QueryWrapper<StorageTrayMaterial> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("tray", tray);
        objectQueryWrapper.eq("material_id", materialId);

        Long stmId = null;
        StorageTrayMaterial db = storageTrayMaterialMapper.selectOne(objectQueryWrapper);
        if (db == null) {
            StorageTrayMaterial storageRegionMaterial = new StorageTrayMaterial();
            storageRegionMaterial.setMaterialId(materialId);
            storageRegionMaterial.setTray(tray);
            storageRegionMaterial.setCount(BigDecimal.ZERO);
            storageRegionMaterial.setCreateTime(now);
            storageRegionMaterial.setUpdateTime(now);
            storageTrayMaterialMapper.insert(storageRegionMaterial);
            stmId = storageRegionMaterial.getId();
        } else {
            stmId = db.getId();
        }
        Subject currentUser = SecurityUtils.getSubject();
        Staff user = (Staff) currentUser.getPrincipal();
        form.setCreator(user.getId());
        form.setCreateTime(now);
        form.setUpdateTime(now);
        storageLogMapper.insert(form);
        int row = storageTrayMaterialMapper.updateNum(stmId, form.getNum());
        if (row <= 0) {
            throw new ResultException(ResultStatus.MATERIAL_NOT_ENOUGH);
        }
    }
}
