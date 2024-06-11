package com.scoks.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.Constant;
import com.scoks.order.entity.Customer;
import com.scoks.order.entity.Partner;
import com.scoks.order.entity.Staff;
import com.scoks.order.exception.Result;
import com.scoks.order.service.AdminServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scoks/api/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping(value = "staff_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object queryAdminList(Page<Staff> page, Staff form) throws Exception {
        return adminService.findStaffPageList(page, form);
    }


    @RequiresAuthentication
    @GetMapping(value = "/staff_edit")
    public Result staff_edit(@RequestParam(value = "id", required = false) Long id) {
        if (id != null && id > 0) {
            Staff user = adminService.getStaffById(id);
            user.setPassword(Constant.PWD_FLAG);
            return Result.success(user);
        } else
            return null;
    }


    @RequiresAuthentication
    @PostMapping(value = "/staff_edit")
    public Result staff_edit(Staff form) throws Exception {
        adminService.setStaff(form);
        return Result.success(null);
    }


    @RequestMapping(value = "customer_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object queryCustomerList(Page<Customer> page, Customer form) throws Exception {
        return adminService.findCustomerPageList(page, form);
    }


    @RequiresAuthentication
    @GetMapping(value = "/customer_edit")
    public Object customer_edit(@RequestParam(value = "id", required = false) Long id) {
        if (id != null && id > 0) {
            Customer user = adminService.getCustomerById(id);
            return Result.success(user);
        } else
            return null;
    }


    @RequiresAuthentication
    @PostMapping(value = "/customer_edit")
    public Object customer_edit(Customer form) throws Exception {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        adminService.setCustomer(form);
        return Result.success(null);
    }


    @RequestMapping(value = "partner_list", method = RequestMethod.GET)
    @RequiresAuthentication
    public Object queryPartnerList(Page<Partner> page, Partner form) throws Exception {
        return adminService.findPartnerPageList(page, form);
    }


    @RequiresAuthentication
    @GetMapping(value = "/partner_edit")
    public Object partner_edit(@RequestParam(value = "id", required = false) Long id) {
        if (id != null && id > 0) {
            Partner user = adminService.getPartnerById(id);
            return Result.success(user);
        } else
            return null;
    }


    @RequiresAuthentication
    @PostMapping(value = "/partner_edit")
    public Object customer_edit(Partner form) throws Exception {
        Staff loginUser = getLoginUser();
        if (form.getId() == null) {
            form.setCreator(loginUser.getId());
        }
        adminService.setPartner(form);
        return Result.success(null);
    }

}
