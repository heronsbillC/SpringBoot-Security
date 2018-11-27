package com.clubfactory.demo.test.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 基础类 MAPPER
 */
public interface IBaseMapper<T> {

    /**
     * 插入记录
     * @param obj
     * @return
     */
    int insert(T obj);

    /**
     * 插入记录(有效字段,即非空字段)
     * @param obj
     * @return
     */
    int insertSelective(T obj);


    int upsertSelective(T obj);

    /**
     * 物理删除记录
     * @param seq
     * @return
     */
    <K> int deleteByPrimaryKey(@Param("id") K seq);

    /**
     * 更新记录(有效字段,即非空字段)
     * @param obj
     * @return
     */
    int updateByPrimaryKeySelective(T obj);

    /**
     * 根据主键 返回记录
     * @param seq
     * @return
     */
    <K> T selectByPrimaryKey(@Param("id") K seq);

    /**
     * 根据 条件返回记录
     * @param params
     * @return
     */
    List<T> selectListByParams(Map<String, Object> params);

}
