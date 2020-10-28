package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.RestaurantServiceRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaw
 * @description: TODO
 * @date xiaw modify on 2020/8/2319:52
 */
@Repository
public interface RestaurantServiceRelateDao {
    /**
     * 新增
     */
    public int insert(@Param("restaurantServiceRelate") RestaurantServiceRelate restaurantServiceRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("restaurantServiceRelate") RestaurantServiceRelate restaurantServiceRelate);

    /**
     * Load查询
     */
    public RestaurantServiceRelate load(@Param("id") int id);

    /**
     * 根据服务特色删除门店信息
     * @param serviceId
     * @return
     */
    public int deleteRestaurantServiceRelate(@Param("serviceId") long serviceId);

    /**
     * 批量插入门店信息
     * @param featureServiceList
     * @return
     */
    public int insertRestaurantServiceRelate(@Param("featureServiceList") List<RestaurantServiceRelate> featureServiceList);

}
