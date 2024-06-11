package com.scoks.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.Constant;
import com.scoks.order.entity.Dict;
import com.scoks.order.entity.MachineInfo;
import com.scoks.order.entity.MachineModel;
import com.scoks.order.entity.Staff;
import com.scoks.order.exception.Result;
import com.scoks.order.service.AdminServiceImpl;
import com.scoks.order.service.SystemServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoks/api/sys")
public class SystemController extends BaseController {

    @Autowired
    private SystemServiceImpl systemService;
    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping(value = "create_supper_role", method = RequestMethod.POST)
    public Object createSupperRole(Staff staff) throws Exception {
        adminService.setStaff(staff);
        return Result.success(null);
    }

    @RequestMapping(value = "dict", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object listDict(Page<Dict> page, Dict form) throws Exception {
        return systemService.findDictPageList(page, form);
    }


    @RequiresAuthentication
    @PostMapping(value = "/dict_edit")
    public Result dict_edit(Dict dict) {
        systemService.setDict(dict);
        return Result.success(null);
    }


    @RequestMapping(value = "machine", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object listMachine(Page<MachineInfo> page, MachineInfo form) throws Exception {
        return systemService.findMachinePageList(page, form);
    }


    @RequiresAuthentication
    @PostMapping(value = "/machine")
    @RequiresRoles(value = {Constant.POSITION_BOSS})

    public Result machine_edit(MachineInfo machineInfo) {
        if (machineInfo.getId() == null) {
            machineInfo.setCreator(getLoginUser().getId());
        }
        systemService.setMachine(machineInfo);
        return Result.success(null);
    }


    @RequestMapping(value = "machine_model", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object listMachineModel(Page<MachineModel> page, MachineModel form) {
        return systemService.findMachineModelPageList(page, form);
    }


    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_BOSS})

    @PostMapping(value = "/machine_model")
    public Result machine_edit(MachineModel machineModel) {
        systemService.setMachineModel(machineModel);
        return Result.success(null);
    }


}
