package com.scoks.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.Dict;
import com.scoks.order.entity.MachineInfo;
import com.scoks.order.entity.MachineModel;
import com.scoks.order.mapper.DictMapper;
import com.scoks.order.mapper.MachineInfoMapper;
import com.scoks.order.mapper.MachineModelMapper;
import com.scoks.order.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl {
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private MachineInfoMapper machineInfoMapper;
    @Autowired
    private MachineModelMapper machineModelMapper;


    public Page<Dict> findDictPageList(Page<Dict> page, Dict form) {
        QueryWrapper<Dict> objectQueryWrapper = new QueryWrapper<>(form);
        objectQueryWrapper.eq("state", 0);
        if (!Utils.stringIsNullOrEmpty(form.getKey())) {
            objectQueryWrapper.like("key", form.getKey());
        }

        IPage<Dict> adminUserIPage = dictMapper.selectPage(page, objectQueryWrapper);
        return page;
    }

    public List<Dict> listDictByGroup(String group) {
        QueryWrapper<Dict> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("state", 0);
        objectQueryWrapper.eq("`group`", group);

        return dictMapper.selectList(objectQueryWrapper);
    }


    public void setDict(Dict form) {
        if (form.getId() == null) {//新增
            form.setState(0);
            if (form.getParentId() == null) {
                form.setParentId(0L);
            }
            if (form.getSort() == null) {
                form.setSort(0);
            }
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            dictMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            dictMapper.updateById(form);
        }

    }


    public Page<MachineInfo> findMachinePageList(Page<MachineInfo> page, MachineInfo form) {
        List<MachineInfo> machineInfos = machineInfoMapper.listMachineInfo(page, form);
        page.setRecords(machineInfos);
        return page;
    }

    public void setMachine(MachineInfo form) {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            machineInfoMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            machineInfoMapper.updateById(form);
        }
    }


    public Page<MachineModel> findMachineModelPageList(Page<MachineModel> page, MachineModel form) {
        QueryWrapper<MachineModel> objectQueryWrapper = new QueryWrapper<>(form);
        if (!Utils.stringIsNullOrEmpty(form.getPinNum())) {
            objectQueryWrapper.eq("pin_num", form.getPinNum());
        }
        if (!Utils.stringIsNullOrEmpty(form.getPinType())) {
            objectQueryWrapper.eq("pin_type", form.getPinType());
        }

        IPage<MachineModel> adminUserIPage = machineModelMapper.selectPage(page, objectQueryWrapper);
        return page;
    }

    public void setMachineModel(MachineModel form) {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            machineModelMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            machineModelMapper.updateById(form);
        }
    }




}
