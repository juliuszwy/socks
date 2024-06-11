package com.scoks.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.Constant;
import com.scoks.order.dto.MachineWorkSum;
import com.scoks.order.service.StatisticsServiceImpl;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoks/api/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @RequestMapping(value = "sum_machine_work", method = RequestMethod.GET)
    @RequiresAuthentication
    @RequiresRoles(value = {Constant.POSITION_BOSS, Constant.POSITION_SALESMAN}, logical = Logical.OR)
    public Object sumMachineWork(Page<MachineWorkSum> page, MachineWorkSum form) throws Exception {
        return statisticsService.sumMachineWork(page, form);
    }


}
