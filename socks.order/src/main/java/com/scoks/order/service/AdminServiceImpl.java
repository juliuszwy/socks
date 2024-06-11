package com.scoks.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.Constant;
import com.scoks.order.entity.Customer;
import com.scoks.order.entity.Dict;
import com.scoks.order.entity.Partner;
import com.scoks.order.entity.Staff;
import com.scoks.order.mapper.CustomerMapper;
import com.scoks.order.mapper.DictMapper;
import com.scoks.order.mapper.PartnerMapper;
import com.scoks.order.mapper.StaffMapper;
import com.scoks.order.utils.Md5Util;
import com.scoks.order.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminServiceImpl {
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PartnerMapper partnerMapper;
    @Autowired
    private DictMapper dictMapper;

    public Page<Staff> findStaffPageList(Page<Staff> page, Staff form) {
        QueryWrapper<Staff> objectQueryWrapper = new QueryWrapper<>();
        //objectQueryWrapper.eq("state",0);
        if (!Utils.stringIsNullOrEmpty(form.getLoginName())) {
            objectQueryWrapper.like("login_name", form.getLoginName());
        }
        if (!Utils.stringIsNullOrEmpty(form.getName())) {
            objectQueryWrapper.like("name", form.getName());
        }
        if (form.getPosition() != null) {
            objectQueryWrapper.eq("position", form.getPosition());
        }
        if (form.getState() != null) {
            objectQueryWrapper.eq("state", form.getState());
        }

        IPage<Staff> adminUserIPage = staffMapper.selectPage(page, objectQueryWrapper);
        List<Staff> records = page.getRecords();
        for (Staff staff : records) {
            staff.setPassword(Constant.PWD_FLAG);
        }
        return page;
    }

    public Staff getStaffById(Long id) {
        Staff user = staffMapper.selectById(id);
        return user;
    }


    public void setStaff(Staff form) throws NoSuchAlgorithmException {
        if (form.getId() == null) {//新增
            String salt = Utils.randomStr(6);
            form.setPassword(Md5Util.encryptMD5(salt + form.getPassword()));
            form.setSalt(salt);
            form.setState(0);
            form.setRole(0);
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            staffMapper.insert(form);
        } else {
            String pwd = form.getPassword();
            if (pwd.equals(Constant.PWD_FLAG)) {
                form.setPassword(null);
            } else {
                String salt = Utils.randomStr(6);
                form.setPassword(Md5Util.encryptMD5(salt + form.getPassword()));
                form.setSalt(salt);
                form.setUpdateTime(System.currentTimeMillis());
            }
            staffMapper.updateById(form);
        }

    }

    public Staff getStaffByName(String loginName) {
        return staffMapper.selectOne(new QueryWrapper<Staff>().eq("login_name", loginName).eq("state", 0));
    }


    public Page<Customer> findCustomerPageList(Page<Customer> page, Customer where) {
        List<Customer> customers = customerMapper.selectCustomer(page, where);
        page.setRecords(customers);
        return page;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = customerMapper.selectById(id);
        if (customer.getSalesman() != null) {
            Staff staff = staffMapper.selectById(customer.getSalesman());
            customer.setSalesmanName(staff.getName());
        }
        if (customer.getCountry() != null) {
            Dict dict = dictMapper.selectById(customer.getCountry());
            if (dict != null)
                customer.setCountryName(dict.getKey());
        }
        return customer;
    }


    public void setCustomer(Customer form) throws NoSuchAlgorithmException {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            form.setState(0);
            customerMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            customerMapper.updateById(form);
        }

    }

    public List<Customer> selectAllCustomers() {
        List<Customer> customers = customerMapper.selectCustomer(null, new Customer());
        return customers;
    }


    public Page<Partner> findPartnerPageList(Page<Partner> page, Partner where) {
        List<Partner> partners = partnerMapper.selectPartner(page, where);
        page.setRecords(partners);
        return page;
    }

    public Partner getPartnerById(Long id) {
        Partner Partner = partnerMapper.selectById(id);
        return Partner;
    }


    public void setPartner(Partner form) throws NoSuchAlgorithmException {
        if (form.getId() == null) {//新增
            long l = System.currentTimeMillis();
            form.setCreateTime(l);
            form.setUpdateTime(l);
            form.setState(0);
            partnerMapper.insert(form);
        } else {
            form.setUpdateTime(System.currentTimeMillis());
            partnerMapper.updateById(form);
        }

    }

    public List<Partner> selectAllPartners() {
        List<Partner> partners = partnerMapper.selectPartner(null, new Partner());
        return partners;
    }


}
