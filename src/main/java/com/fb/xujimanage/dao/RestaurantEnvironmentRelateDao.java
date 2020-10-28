package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.RestaurantEnvironmentRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:55
 * @description:
 */
@Repository
public interface RestaurantEnvironmentRelateDao {
    /**
     * 新增
     */
    public int insert(@Param("restaurantEnvironmentRelate") RestaurantEnvironmentRelate restaurantEnvironmentRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("restaurantEnvironmentRelate") RestaurantEnvironmentRelate restaurantEnvironmentRelate);

    /**
     * Load查询
     */
    public RestaurantEnvironmentRelate load(@Param("id") int id);

    /**
     * 根据环境配置id删除门店关联信息
     * @param environmentId
     * @return
     */
    public int deleteByEnvironmentId(long environmentId);

    /**
     * 批量插入门店信息
     * @param list
     * @return
     */
    public int insertRestaurantEnvironmentRelateList(@Param("list") List<RestaurantEnvironmentRelate> list);
}
