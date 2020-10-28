package com.fb.xujimanage.service;

import com.fb.xujimanage.entity.dto.OrderElectricPageDto;
import com.fb.xujimanage.entity.vo.OrderElectricDetailsVo;
import com.fb.xujimanage.entity.vo.OrderElectricPageVo;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;

public interface OrderElectricService {
    /**
     * 功能描述  查询到店点单订单分页列表数据
     * @author false_老默
     * @param dto
     * @date 2020/9/14 17:07
     * @return com.github.pagehelper.PageInfo<com.fb.xujimanage.entity.vo.OrderElectricPageVo>
     */
    PageInfo<OrderElectricPageVo> pageList(OrderElectricPageDto dto) ;
    
    /**
     * 功能描述 根据套餐ID查询到店订单菜品中套餐中详情
     * @author false_老默
     * @param mealId 套餐ID
     * @param orderId 订单ID
     * @date 2020/9/15 10:57 
     * @return java.util.List<com.fb.xujimanage.entity.vo.OrderElectricDetailsVo>
     */
    List<OrderElectricDetailsVo> getOrderMealDetailsByMealId(Long mealId ,Long orderId);
}
