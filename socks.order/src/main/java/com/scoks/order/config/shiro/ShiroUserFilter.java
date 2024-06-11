package com.scoks.order.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.scoks.order.exception.Result;
import com.scoks.order.exception.ResultStatus;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ShiroUserFilter extends UserFilter {
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(Result.error(ResultStatus.NOT_LOGGED_IN)));
    }
}
