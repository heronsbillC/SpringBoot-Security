package com.clubfactory.demo.test.datasource;

import java.lang.annotation.*;

/**
 * 动态切换数据源（默认读取主库）
 * master 主库 ，slave 从库
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSource {
    /**
     * 数据源名称
     */
    String value();
}
