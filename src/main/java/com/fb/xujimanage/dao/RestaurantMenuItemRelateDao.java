package com.fb.xujimanage.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fb.xujimanage.entity.RestaurantMenuItemRelate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/21 8:02
 * @description:
 */
@Repository
public interface RestaurantMenuItemRelateDao extends BaseMapper<RestaurantMenuItemRelate> {
    /**
     * 新增
     */
    public int insert(@Param("restaurantMenuItemRelate") RestaurantMenuItemRelate restaurantMenuItemRelate);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("restaurantMenuItemRelate") RestaurantMenuItemRelate restaurantMenuItemRelate);

    /**
     * Load查询
     */
    public RestaurantMenuItemRelate load(@Param("id") int id);

    /**
     * 根据菜品id删除门店信息
     * @param menuitemId
     * @return
     */
    public int deleteByMenuitemId(@Param("menuitemId") Long menuitemId);

    /**
     * 批量插入数据
     * @param restaurantMenuItemRelates
     * @return
     */
    public int insertRestaurantMenuItemRelateList(List<RestaurantMenuItemRelate> restaurantMenuItemRelates);


    RestaurantMenuItemRelate selectOne(@Param("restaurantId") Long restaurantId,@Param("menuItemId") long menuItemId);


    int deletByRestaurantId(@Param("restaurantId")  Long restaurantId);






}
