package com.scoks.order.controller;

import com.scoks.order.Enums;
import com.scoks.order.entity.Customer;
import com.scoks.order.entity.Dict;
import com.scoks.order.entity.Staff;
import com.scoks.order.exception.Result;
import com.scoks.order.exception.ResultStatus;
import com.scoks.order.service.AdminServiceImpl;
import com.scoks.order.service.SystemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@Slf4j
@RequestMapping("/scoks/api")
public class LoginController extends BaseController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private SystemServiceImpl systemService;

    @PostMapping("/login")
    public Object login(Staff req, HttpServletResponse response) {
        String oper = "用户登录";
        log.info("{}, body:{}", req);
        String name = req.getLoginName();
        String pwd = req.getPassword();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            return Result.error(ResultStatus.FORBIDDEN);
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            //登录
            currentUser.login(new UsernamePasswordToken(name, pwd));
            //从session取出用户信息
            Staff user = (Staff) currentUser.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }
            return Result.success(null);
        } catch (AuthenticationException ae) {
            log.warn("用户帐号不正确");
        }
        return Result.error(ResultStatus.LOGIN_ERR);
    }

    @GetMapping("/login")
    @RequiresAuthentication
    public Object getLogin() {
        Map<String, Object> result = new HashMap<>();
        Staff loginUser = getLoginUser();

        Staff user = new Staff();
        user.setLoginName(loginUser.getLoginName());
        user.setName(loginUser.getName());
        user.setPhone(loginUser.getPhone());
        user.setEmail(loginUser.getEmail());
        user.setPosition(loginUser.getPosition());
        user.setState(loginUser.getState());
        user.setId(loginUser.getId());
        result.put("user", user);

        if (loginUser.getPosition() != Enums.Position.OUT.position()) {
            List<Customer> customers = adminService.selectAllCustomers();
            Map<String, List<Customer>> map = new HashMap<>();
            if (customers != null && !customers.isEmpty()) {
                customers.forEach(v -> {
                    List<Customer> cs = map.get(v.getCountry() + "");
                    if (cs == null) {
                        cs = new ArrayList<>();
                        map.put(v.getCountry() + "", cs);
                    }

                    cs.add(v);
                });
            }
            List<Dict> dictRes = new ArrayList<>();
            List<Dict> dicts = systemService.listDictByGroup("country");
            dicts.forEach(v -> {
                List<Customer> customerList = map.get(v.getId() + "");
                if (customerList != null) {
                    //如果是业务员判断是否可以显示国家
                    if (loginUser.getPosition() == Enums.Position.SALESMAN.position()) {
                        Iterator<Customer> iterator = customerList.iterator();
                        while (iterator.hasNext()) {
                            Customer customer = iterator.next();
                            if (customer.getSalesman() != loginUser.getId()) {
                                iterator.remove();
                            }
                        }
                    }
                    if (!customerList.isEmpty()) {
                        v.setObj(customerList);
                        dictRes.add(v);
                    }
                }
            });

            result.put("customers", dictRes);
        }
        return result;
    }

}
