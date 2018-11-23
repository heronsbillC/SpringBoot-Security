package com.clubfactory.demo.test.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
@MapperScan(value = "com.clubfactory.demo.test.mapper")
@EnableTransactionManagement
@Slf4j
public class MyBatisConfig {
    @Resource
    private MultipleDataSource multipleDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactories() throws Exception {
        log.info("-------------------- 重载父类 sqlSessionFactory init ---------------------");
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(multipleDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        fb.setMapperLocations(resolver.getResources("classpath:/mapper/**/*Mapper.xml"));
        return fb.getObject();
    }
}
