package com.fb.xujimanage.dao;

import com.fb.xujimanage.entity.OrderElectric;
import com.fb.xujimanage.entity.vo.OrderElectricDetailsVo;
import com.fb.xujimanage.entity.vo.OrderElectricPageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OrderElectricDao {


    /**
     * 功能描述 查询到店点单订单分页数据
     * @author false_老默
     * @param cityCode 城市编码
     * @param nameOrOrderNo 下单门店名称or订单编号
     * @param orderTimeBegin 下单开始时间
     * @param orderTimeEnd 下单结束时间
     * @date 2020/9/14 17:10
     * @return java.util.List<com.fb.xujimanage.entity.vo.OrderElectricPageVo>
     */
    List<OrderElectricPageVo> pageList(@Param("cityCode")String cityCode,
                                       @Param("nameOrOrderNo")String nameOrOrderNo,
                                       @Param("orderTimeBegin") String orderTimeBegin,
                                       @Param("orderTimeEnd")String orderTimeEnd);

    /**
     * 功能描述 根据套餐ID查询到店订单菜品中套餐中详情
     * @author false_老默
     * @param mealId 套餐ID
     * @param orderId 订单ID
     * @date 2020/9/15 10:57
     * @return java.util.List<com.fb.xujimanage.entity.vo.OrderElectricDetailsVo>
     */
    List<OrderElectricDetailsVo> getOrderMealDetailsByMealId( @Param("mealId") Long mealId,@Param("orderId") Long orderId);
}
