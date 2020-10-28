package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.MenuItem;
import com.fb.xujimanage.entity.copy.MenuItemsCopy;
import com.fb.xujimanage.entity.copy.MenultemCopy;
import com.fb.xujimanage.entity.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:49
 * @description:菜单信息
 */
@Repository
public interface MenuItemDao {
    /**
     * 新增
     */
    int insert(@Param("menuItem") MenuItem menuItem);

    /**
     * 删除
     */
    int delete(@Param("id") int id);

    /**
     * 更新
     */
    int update(@Param("menuItem") MenuItem menuItem);

    /**
     * Load查询
     */
    MenuItem load(@Param("id") String id);

    /**
     * 分页查询所有菜品信息
     *
     * @param menuitemReqVo
     * @return
     */
    List<MenuItem> loadMenUItem(@Param("menuitemReqVo") MenuitemReqVo menuitemReqVo, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 查詢分页总条数
     *
     * @param menuitemReqVo
     * @return
     */
    int getMenuItemTotal(@Param("menuitemReqVo") MenuitemReqVo menuitemReqVo);

    /**
     * 通过菜品id查询菜品详情
     *
     * @param menuItemId
     * @return
     */
    MenuItem loadMenUItemById(@Param("menuItemId") String menuItemId, @Param("dietCode") String dietCode, @Param("restaurantCode") String restaurantCode);

    /**
     * 获取菜品信息
     *
     * @return
     */
    List<MenuItemVo> getMenuItemList();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    MenultemCopy selectOneById(@Param("id") String id);

    /**
     * 修改单条
     *
     * @param menultemCopy
     * @return
     */
    int updateOne(@Param("menultemCopy") MenultemCopy menultemCopy);

    /**
     * 添加单条
     *
     * @param menultemCopy
     * @return
     */
    int insertOne(@Param("menultemCopy") MenultemCopy menultemCopy);

    /**
     * 根据订单id查询订单内菜品列表
     *
     * @param orderId
     * @param itemType
     * @return
     */
    List<MenuItemInfoVo> listMenuItemByOrderId(@Param("orderId") Long orderId, @Param("mealId") String mealId, @Param("itemType") Integer itemType);

    /**
     * 根据订单id查询订单内套餐列表
     *
     * @param orderId
     * @param itemType
     * @return
     */
    List<MealInfoVo> listMealRelateByOrderId(@Param("orderId") Long orderId, @Param("itemType") Integer itemType);

    /**
     * 根据套餐id查询套餐菜品列表
     *
     * @param mealId
     */
    List<OrderMealVo> queryMenuItemByMealId(Long mealId);

    /**
     * 根据订单id查询订单内套餐和菜品列表
     *
     * @param orderId
     * @return
     */
    List<OrderMealInfoVo> listOrderMealByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据ID 批量查询
     *
     * @param ids
     * @return
     */
    List<MenultemCopy> selectMenuItemCopy(@Param("ids") List<String> ids);

    /**
     * 根据门店code查询菜品信息
     *
     * @param restaurantCode
     * @return
     */
    List<MenultemCopy> selectMenuItemByRestaurantCode(@Param("restaurantCode") String restaurantCode);

    /**
     * 根据门店code删除菜品信息
     *
     * @param restaurantCode
     * @return
     */
    int deleteMenuItemByRestaurantCode(@Param("restaurantCode") String restaurantCode);

    /**
     * 根据ID 删除
     *
     * @param ids
     * @return
     */
    int deleteMenuItemCopy(@Param("ids") List<String> ids);


    int insertMenuItemCopy(@Param("menultemCopies") List<MenultemCopy> menultemCopies);

    /**
     * 批量更新库存以及have_kind
     *
     * @param menuItemsCopies
     * @return
     */
    int updateMenuItem(List<MenuItemsCopy> menuItemsCopies);

    /**
     * 通过id查询和门店code查询菜品信息
     *
     * @param id
     * @param restaurantCode
     * @return
     */
    MenuItem getMenuItemByRestaurantCode(@Param("id") String id, @Param("restaurantCode") String restaurantCode);

    /**
     * 汇总菜品数量
     *
     * @return
     */
    int menuItemTotalCount();
}
