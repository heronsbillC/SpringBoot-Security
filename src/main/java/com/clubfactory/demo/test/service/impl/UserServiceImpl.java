package com.clubfactory.demo.test.service.impl;

import com.clubfactory.demo.test.datasource.DataSource;
import com.clubfactory.demo.test.mapper.RoleMapper;
import com.clubfactory.demo.test.mapper.UsersMapper;
import com.clubfactory.demo.test.pojo.Role;
import com.clubfactory.demo.test.pojo.RoleExample;
import com.clubfactory.demo.test.pojo.Users;
import com.clubfactory.demo.test.pojo.UsersExample;
import com.clubfactory.demo.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.clubfactory.demo.test.datasource.DataSourceConstant.TEST;

/**
 * 为框架提供一个实现了UserDetailsService接口的类
 */
@Service
@Slf4j
public class UserServiceImpl implements /*UserService,*/UserDetailsService {
    /**
     * 根据用户名 返回一个UserDetails的实现类的实例
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = getUserByName(s);
        if(user == null)
            throw new UsernameNotFoundException("没有该用户");
        return new UserDetailImpl(user,getRole(user.getRoleId()));
    }

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private RoleMapper roleMapper;

//    @Override
    @DataSource(TEST)
    public Users login(String userName, String password) {
        UsersExample usersExample = new UsersExample();
        Users user = null;
        if(userName != null && password != null) {
            usersExample.createCriteria().andNameEqualTo(userName).andPasswordEqualTo(password);
            user = usersMapper.selectByExample(usersExample).get(0);
        }
        log.info("UserServiceImpl.login->user=" + user);
        return user;
    }

    /**
     * 根据角色ID寻找角色名
     * @param roleId
     * @return
     */
    @DataSource(TEST)
    private List<Role> getRole(int roleId){
        //一个用户对应多个角色，但是在此测试中一个用户对应一个角色
        List<Role> roles = new ArrayList<>();
        Role role = roleMapper.selectByPrimaryKey(roleId);
        roles.add(role);
        log.info("UserServiceImpl.getRole->role=" + role);
        return roles;
    }

    @DataSource(TEST)
    private Users getUserByName(String name){
        UsersExample example = new UsersExample();
        example.createCriteria().andNameEqualTo(name);
        return usersMapper.selectByExample(example).get(0);
    }
}