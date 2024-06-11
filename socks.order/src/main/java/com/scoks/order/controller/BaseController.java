package com.scoks.order.controller;

import com.scoks.order.entity.Staff;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    private HttpServletRequest request;

    protected Staff getLoginUser() {
        Subject currentUser = SecurityUtils.getSubject();
        return (Staff) currentUser.getPrincipal();
    }

}
