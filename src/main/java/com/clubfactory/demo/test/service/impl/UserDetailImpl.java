package com.clubfactory.demo.test.service.impl;

import com.clubfactory.demo.test.pojo.Role;
import com.clubfactory.demo.test.pojo.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 为框架实现UserDetails接口
 */
@Component
@NoArgsConstructor
@Data
public class UserDetailImpl implements UserDetails {
    private String userName;
    private String password;
    /**
     * 用户对应的role
     */
    private List<Role> roles;

    /**
     * 用User和List<Role>构造
     * @param user
     * @param roles
     */
    public UserDetailImpl(Users user, List<Role> roles) {
        this.userName = user.getName();
        this.password = user.getPassword();
        this.roles = roles;
    }

    /**
     * 返回用户所有角色的封装，一个Role对应一个GrantedAuthority
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 判断用户是否过期，默认没有过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断用户是否锁定，默认没有锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断信用凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断账号是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
