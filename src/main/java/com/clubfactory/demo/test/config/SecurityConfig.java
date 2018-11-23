package com.clubfactory.demo.test.config;

import com.clubfactory.demo.test.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin").access("hasRole('ADMIN')")
                    .antMatchers("/index").access("hasRole('USER')")
                    .antMatchers("/hello").permitAll()  //不需要权限也可以访问
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("username")     //指定页面中对应用户名的参数名称
                    .passwordParameter("password")     //指定页面中对应密码的参数名称
                    .defaultSuccessUrl("/hello")
                    .failureUrl("/error");
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
