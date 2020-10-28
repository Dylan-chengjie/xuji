package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.OrderDinner;
import com.fb.xujimanage.entity.vo.OrderDinnerInfoVo;
import com.fb.xujimanage.entity.vo.OrderDinnerVo;
import com.fb.xujimanage.entity.vo.UserCustomerInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author chengjie
 * @version 1.0
 * @date 2020/8/20 19:33
 * @description: 预点订单
 */
@Repository
public interface OrderDinnerDao {

    /**
     * 更新订单
     *
     * @param orderDinner
     * @return Integer
     */
    Integer updateOrderDinner(OrderDinner orderDinner);

    /**
     * 查询订单
     *
     * @param city
     * @param area
     * @param restaurantName
     * @param timeStart
     * @param timeEnd
     * @param orderTimeStart
     * @param orderTimeEnd
     * @param orderStatus
     * @return List<OrderDinner>
     */
    List<OrderDinnerVo> pageOrderDinner(@Param("city") String city, @Param("area") String area, @Param("restaurantName") String restaurantName,
                                        @Param("timeStart") Time timeStart, @Param("timeEnd") Time timeEnd, @Param("orderTimeStart") Date orderTimeStart,
                                        @Param("orderTimeEnd") Date orderTimeEnd, @Param("orderStatus") Integer orderStatus);

    /**
     * 查询订单信息
     *
     * @param startTime
     * @return
     */
    List<OrderDinnerInfoVo> listOrderDinner(@Param("restaurantId") Long restaurantId, @Param("startTime") Date startTime);

    /**
     * 查询订单信息
     *
     * @param customerIds
     * @return
     */
    List<UserCustomerInfoVo> queryUserCustomerInfo(@Param("customerIds") Set<Long> customerIds);
}
