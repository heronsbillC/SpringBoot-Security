package com.clubfactory.demo.test.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Bean(name = "test")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DruidDataSource dataSourceTest(){
        log.info("-----------------testDataSource-------------");
        return DruidDataSourceBuilder.create().build();
    }

}
