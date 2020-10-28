package com.fb.xujimanage.service;


import com.fb.xujimanage.entity.OrderDinner;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-20 19:18
 * @description:订单逻辑接口类
 * @version:
 */
public interface OrderDinnerService {

    /**
     * 更新订单
     *
     * @param orderDinner
     * @return
     */
    CommonResult updateOrderDinner(OrderDinner orderDinner);

    /**
     * 查询订单
     *
     * @param pageNum
     * @param pageSize
     * @param city
     * @param area
     * @param restaurantName
     * @param timeStart
     * @param timeEnd
     * @param orderTimeStart
     * @param orderTimeEnd
     * @param orderStatus
     * @return
     */
    CommonResult<PageInfo<List<OrderDinnerVo>>> pageOrderDinner(Integer pageNum, Integer pageSize, String city,
                                                                String area, String restaurantName, Time timeStart, Time timeEnd
            , Date orderTimeStart, Date orderTimeEnd, Integer orderStatus);

    /**
     * 根据时间区间获取订单信息
     *
     * @param startTime
     */
    CommonResult<List<OrderDinnerInfoVo>> listOrderDinner(Long restaurantId, Date startTime);

    /**
     * 根据订单id查询订单菜品信息
     *
     * @param orderId
     */
    CommonResult<List<OrderMenuItemVo>> queryMenuItemByOrderId(Long orderId);

    /**
     * 根据套餐id查询套餐菜品列表
     *
     * @param mealId
     */
    CommonResult<List<OrderMealVo>> queryMenuItemByMealId(Long mealId);
}
