package com.clubfactory.demo.test.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MultipleDataSource extends AbstractRoutingDataSource {
    private final static ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public static void setDataSourceKey(String dataSource){
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = dataSourceKey.get();
        return dataSource;
    }
}
