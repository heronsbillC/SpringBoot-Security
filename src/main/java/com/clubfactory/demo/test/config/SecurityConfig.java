package com.clubfactory.demo.test.config;

import com.clubfactory.demo.test.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers( "/LoginController/home","/LoginController/loginAction").permitAll()
                .antMatchers("/LoginController/admin").access("hasRole('ADMIN')")
                .antMatchers("/LoginController/user").access("hasRole('USER')")
                //其他地址的访问均需验证权限
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/LoginController/login")
                .defaultSuccessUrl("/LoginController/hello")//登录成功后默认跳转到"/hello"
                .permitAll()
                .usernameParameter("username")     //指定页面中对应用户名的参数名称
                .passwordParameter("password")     //指定页面中对应密码的参数名称
                .and()
                .logout()
                .logoutSuccessUrl("/LoginController/home")//退出登录后的默认url是"/home"
                .permitAll()
                .and()
                .csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring() //ignoring它是完全绕过spring security的所有filter的；根本不会经过springSecurity的任何拦截，所以适合静态资源的放过
                .antMatchers("/LoginController/loginAction");
    }

    /**
     * 定义认证用户信息获取来源
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserServiceImpl());
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    /*@Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }*/

}
