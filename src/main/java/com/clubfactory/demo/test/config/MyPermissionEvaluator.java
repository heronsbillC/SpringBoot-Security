package com.clubfactory.demo.test.config;

import com.clubfactory.demo.test.service.ResourceService;
import com.clubfactory.demo.test.service.impl.UserServiceImpl;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Resource
    private UserServiceImpl userService;
    @Resource
    private ResourceService resourceService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        //获得loadUserByUsername的结果
        User user = (User) authentication.getPrincipal();
        //获得loadUserByUsername中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        //遍历用户所有角色
        for (GrantedAuthority authority: authorities) {
            String roleName = authority.getAuthority();
            int resourceId = userService.getRoleByRoleName(roleName).getResourceId();
            String url = resourceService.getResource(resourceId);
            // 如果访问的Url和权限用户符合的话，返回true
            if(targetUrl.equals(url))
                return true;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
