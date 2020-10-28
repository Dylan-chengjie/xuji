package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.RestaurantMenuRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:50
 * @description:
 */
@Repository
public interface RestaurantMenuRelateDao {
    /**
     * 新增
     */
    public int insert(RestaurantMenuRelate restaurantMenuRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(RestaurantMenuRelate restaurantMenuRelate);

    /**
     * Load查询
     */
    public RestaurantMenuRelate load(@Param("id") int id);

    /**
     * 根据菜单id查询
     */
    public RestaurantMenuRelate loadByMenuId(@Param("menuId") long menuId);

    /**
     * 批量插入
     * @return
     */
    public int insertRestaurantMenuRelateList(List<RestaurantMenuRelate> restaurantMenuRelateList);

    /**
     * 根据menuId删除
     * @param menuId
     * @return
     */
    public int deleteByMenuId(Long menuId);


}
