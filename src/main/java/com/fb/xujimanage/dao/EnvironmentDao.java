package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.Environment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:44
 * @description:环境布置信息接口
 */
@Repository
public interface EnvironmentDao {
    /**
     * 新增
     */
    public int insert(Environment environment);

    /**
     * 删除
     */
    public Integer delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(Environment environment);

    /**
     * Load查询
     */
    public Environment load(@Param("id") long id);

    /**
     * 一对一级联门店表查询就餐环境信息
     *
     * @return
     */
    public List<Environment> selectEnvironmentInfo(@Param("environmentalName") String environmentalName, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 查询分页数量
     * @param environmentalName
     * @return
     */
    int getEnvironmentTotal(@Param("environmentalName") String environmentalName);

    /**
     * 根据门店和场景id查询环境配置信
     *
     * @param restaurantId
     * @param sceneId
     * @return
     */
    Environment selectEnvironment(@Param("restaurantId") long restaurantId, @Param("sceneId") long sceneId);

    /**
     * 根据名字一对一级联门店表查询就餐环境信息
     *
     * @param name
     * @return
     */
    public List<Environment> selectEnvironmentByName(String name);

    /**
     * 根据环境配置id一对一级联门店表查询就餐环境信息
     *
     * @param environmentId
     * @return
     */
    public List<Environment> selectEnvironmentByName(@Param("environmentId") int environmentId);
}
