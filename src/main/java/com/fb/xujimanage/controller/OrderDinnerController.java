package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.OrderDinner;
import com.fb.xujimanage.entity.vo.MenuItemInfoVo;
import com.fb.xujimanage.entity.vo.OrderDinnerVo;
import com.fb.xujimanage.entity.vo.OrderMealVo;
import com.fb.xujimanage.entity.vo.OrderMenuItemVo;
import com.fb.xujimanage.enums.OrderStatusEnum;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.service.OrderDinnerService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-20 20:37
 * @description:预点订单管理
 * @version:
 */
@Api(description = "预点订单管理")
@RestController
@AuthToken
@RequestMapping("/order/dinner")
public class OrderDinnerController {

    private OrderDinnerService orderDinnerService;

    public OrderDinnerController(OrderDinnerService orderDinnerService) {
        this.orderDinnerService = orderDinnerService;
    }

    @ApiOperation(value = "更新订单,确认是否到场")
    @PutMapping
    public CommonResult updateImgCollect(@RequestBody @Validated({FirstGroup.class}) OrderDinner orderDinner) {
        return orderDinnerService.updateOrderDinner(orderDinner);
    }

    @ApiOperation(value = "分页查询预点订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码,默认第一页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页显示数量,默认为10", dataType = "int"),
            @ApiImplicitParam(name = "city", value = "城市", dataType = "string"),
            @ApiImplicitParam(name = "area", value = "区域", dataType = "string"),
            @ApiImplicitParam(name = "restaurantName", value = "门店名称", dataType = "string"),
            @ApiImplicitParam(name = "timeStart", value = "就餐开始时间", dataType = "time"),
            @ApiImplicitParam(name = "timeEnd", value = "就餐结束时间", dataType = "time"),
            @ApiImplicitParam(name = "orderTimeStart", value = "下单开始时间", dataType = "long"),
            @ApiImplicitParam(name = "orderTimeEnd", value = "下单结束时间", dataType = "long"),
            @ApiImplicitParam(name = "orderStatus", value = "预点单状态： UNPAID 待支付； PAID 已支付；CUSTOMER_CANCEL 客户取消；SYSTEM_CANCEL 系统取消", dataType = "string")
    })
    @GetMapping
    public CommonResult<PageInfo<List<OrderDinnerVo>>> pageOrderDinner(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "restaurantName", required = false) String restaurantName,
            @RequestParam(name = "timeStart", required = false) Time timeStart,
            @RequestParam(name = "timeEnd", required = false) Time timeEnd,
            @RequestParam(name = "orderTimeStart", required = false) Long orderTimeStart,
            @RequestParam(name = "orderTimeEnd", required = false) Long orderTimeEnd,
            @RequestParam(name = "orderStatus", required = false) OrderStatusEnum orderStatus
    ) {
        return orderDinnerService.pageOrderDinner(pageNum, pageSize, city, area, restaurantName,
                timeStart, timeEnd, orderTimeStart != null ? new Date(orderTimeStart) : null,
                orderTimeEnd != null ? new Date(orderTimeEnd) : null, orderStatus != null ? orderStatus.getType() : null);
    }

    @ApiOperation(value = "根据订单号查询菜品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "long"),
    })
    @GetMapping("/queryMenuByOrderId")
    public CommonResult<List<OrderMenuItemVo>> queryMenuItemByOrderId(@RequestParam Long orderId) {
        return orderDinnerService.queryMenuItemByOrderId(orderId);
    }

    @ApiOperation(value = "根据套餐id查询套餐菜品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mealId", value = "套餐id", required = true, dataType = "long"),
    })
    @GetMapping("/queryMenuByMealId")
    public CommonResult<List<OrderMealVo>> queryMenuItemByMealId(@RequestParam Long mealId) {
        return orderDinnerService.queryMenuItemByMealId(mealId);
    }
}
