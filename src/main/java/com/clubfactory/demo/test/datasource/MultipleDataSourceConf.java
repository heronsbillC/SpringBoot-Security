package com.clubfactory.demo.test.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultipleDataSourceConf {

    @Resource(name = "test")
    private DataSource test;

    @Bean
    public MultipleDataSource buildMultipleDataSource(){
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceConstant.TEST,test);
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        multipleDataSource.setTargetDataSources(targetDataSources);
        multipleDataSource.setDefaultTargetDataSource(test);
        return multipleDataSource;
    }
}
