package com.scoks.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.Constant;
import com.scoks.order.dto.MaterialSumDTO;
import com.scoks.order.entity.Material;
import com.scoks.order.entity.StorageLog;
import com.scoks.order.entity.StorageRegion;
import com.scoks.order.entity.StorageTrayMaterial;
import com.scoks.order.exception.Result;
import com.scoks.order.query.MaterialQuery;
import com.scoks.order.query.StorageLogQuery;
import com.scoks.order.service.StorageServiceImpl;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scoks/api/storage")
public class StorageController extends BaseController {

    @Autowired
    private StorageServiceImpl storageService;

    @GetMapping("material")
    @RequiresAuthentication
    public Object listMaterial(Page<Material> page, Material form) throws Exception {
        return storageService.findMaterialPageList(page, form);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_BOSS, Constant.STORAGE}, logical = Logical.OR)
    @PostMapping(value = "/material_edit")
    public Result material_edit(Material dict) {
        Long l = storageService.setMaterial(dict);
        return Result.success(l);
    }

    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_BOSS, Constant.STORAGE}, logical = Logical.OR)
    @PostMapping(value = "/material/saveIfAbsent")
    public Result saveIfAbset(Material dict) {
        return Result.success(storageService.saveIfAbsent(dict));
    }


    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_BOSS})
    @PostMapping(value = "/region_edit")
    public Object setRegion(StorageRegion storageRegion) {
        storageRegion.setOperator(getLoginUser().getId());
        storageService.setStorageRegion(storageRegion);
        return Result.success(null);
    }

    @GetMapping(value = "/region")
    public Object listRegionDetail(Page<StorageRegion> page, StorageRegion query) {
        return storageService.findStorageRegionPageList(page, query);
    }

    @GetMapping(value = "/material/statistics")
    public Object statisticsMaterial(Page<MaterialSumDTO> page, MaterialQuery query) {
        return storageService.statisticsStorageMaterialPageList(page, query);
    }

    @GetMapping(value = "/tray/material")
    public Object listTrayMaterialDetail(Page<StorageTrayMaterial> page, MaterialQuery query) {
        return storageService.findStorageTrayMaterialPageList(page, query);
    }

    @PostMapping(value = "/material_in_out")
    public Result material_in_out(@RequestBody List<StorageLog> forms) {
        for (StorageLog form : forms) {
            storageService.insetStorageLog(form);
        }
        return Result.success(null);
    }

    @GetMapping(value = "/material/logs")
    public Object material_sum_detail(Page<StorageLog> page, StorageLogQuery from) {
        return storageService.findStorageLogPageList(page, from);
    }
}
