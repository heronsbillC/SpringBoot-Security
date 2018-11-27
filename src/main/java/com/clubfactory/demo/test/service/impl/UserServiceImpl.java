package com.clubfactory.demo.test.service.impl;

import com.clubfactory.demo.test.datasource.DataSource;
import com.clubfactory.demo.test.mapper.RoleMapper;
import com.clubfactory.demo.test.mapper.UsersMapper;
import com.clubfactory.demo.test.pojo.Role;
import com.clubfactory.demo.test.pojo.RoleExample;
import com.clubfactory.demo.test.pojo.Users;
import com.clubfactory.demo.test.pojo.UsersExample;
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
public class UserServiceImpl implements UserDetailsService {
    /**
     * 根据用户名 返回一个UserDetails的实现类的实例
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = getUserByName(username);
        if(user == null)
            throw new UsernameNotFoundException("没有该用户");
        return new UserDetailImpl(user,getRole(user.getRoleId()));
    }

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private RoleMapper roleMapper;

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

    /**
     * 根据角色名找到角色
     * @param roleName
     * @return
     */
    public Role getRoleByRoleName(String roleName){
        RoleExample example = new RoleExample();
        example.createCriteria().andRoleEqualTo(roleName);
        Role role = roleMapper.selectByExample(example).get(0);
        return role;
    }

    /**
     * 根据资源id获取到角色，一个资源对应多个角色
     * @param id
     * @return
     */
    public List<Role> getRoleByResourceId(int id){
        RoleExample example = new RoleExample();
        example.createCriteria().andResourceIdEqualTo(id);
        List<Role> roles = new ArrayList<>();
        //此例中一个资源对应一个角色
        Role role = roleMapper.selectByExample(example).get(0);
        roles.add(role);
        return roles;
    }

    @DataSource(TEST)
    private Users getUserByName(String name){
        UsersExample example = new UsersExample();
        example.createCriteria().andNameEqualTo(name);
        return usersMapper.selectByExample(example).get(0);
    }

}
