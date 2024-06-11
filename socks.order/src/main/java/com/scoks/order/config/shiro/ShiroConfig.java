package com.scoks.order.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.scoks.order.utils.Md5Util;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm myShiroRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    @Bean
    public Collection<Realm> realms() {
        Collection<Realm> realms = new ArrayList<>();
        realms.add(myShiroRealm());
        return realms;
    }


    @Bean
    AtLeastOneSuccessfulStrategy authenticationStrategy() {
        return new AtLeastOneSuccessfulStrategy();
    }

    /**
     * 当只有一个Realm时，就使用这个Realm，当配置了多个Realm时，会使用所有配置的Realm。
     *
     * @return
     */

    @Bean
    ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(authenticationStrategy());
        return authenticator;
    }

    /**
     * 处理session有效期
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setGlobalSessionTimeout(86400000);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }


    /**
     * 会话管理器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator());
        securityManager.setRealms(realms());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = new LinkedHashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        filters.put("logout", logoutFilter);
        filters.put("authc", new ShiroUserFilter());
        shiroFilterFactoryBean.setFilters(filters);

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "user");
        map.put("/scoks/api/sys/create_supper_role", "anon");
        map.put("/scoks/api/*/export", "anon");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/scoks/api/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie remeberCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("remeberMe");
        simpleCookie.setMaxAge(86400);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean
    public SimpleCookie sessionCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("JSESSIONID");
        simpleCookie.setMaxAge(86400);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberCookie());
        cookieRememberMeManager.setCipherKey("ZHANGXIAOHEI_CAT".getBytes());

        return cookieRememberMeManager;
    }


    @Bean
    protected Cookie sessionCookieTemplate() {
        return sessionCookie();
    }

    @Bean
    protected Cookie rememberMeCookieTemplate() {
        return remeberCookie();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String admin = Md5Util.encryptMD5("1123456");
        System.out.println(admin);
    }
}