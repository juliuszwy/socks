package com.scoks.order.config.shiro;

import com.scoks.order.Enums;
import com.scoks.order.entity.Permissions;
import com.scoks.order.entity.Role;
import com.scoks.order.entity.Staff;
import com.scoks.order.exception.ResultException;
import com.scoks.order.exception.ResultStatus;
import com.scoks.order.service.AdminServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        Staff user = (Staff) principalCollection.getPrimaryPrincipal();
        if (user == null) return null;
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getPosition() + "");
        if (user.getRoles() != null)
            for (Role role : user.getRoles()) {
                //添加角色
                simpleAuthorizationInfo.addRole(role.getId() + "");
                //添加权限
                if (role.getPermissions() != null)
                    for (Permissions permissions : role.getPermissions()) {
                        simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
                    }
            }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        Staff user = adminService.getStaffByName(name);
        if (user == null || user.getPosition() == Enums.Position.WORKER.position()) {
            throw new ResultException(ResultStatus.FORBIDDEN);
        } else {
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
            return simpleAuthenticationInfo;
        }
    }

}
