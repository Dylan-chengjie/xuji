package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.MenuItemDao;
import com.fb.xujimanage.dao.OrderDinnerDao;
import com.fb.xujimanage.dao.RestaurantDao;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.OrderDinner;
import com.fb.xujimanage.entity.vo.*;
import com.fb.xujimanage.service.OrderDinnerService;
import com.fb.xujimanage.util.CommonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengjie
 * @date 2020-08-20 20:24
 * @description:预点订单逻辑实现类
 * @version:
 */
@Service
public class OrderDinnerServiceImpl implements OrderDinnerService {

    private  static  final  Integer verify_confirm =1;//确认到场
    private  static  final  Integer order_completed=1;//订单已完成

    private OrderDinnerDao orderDinnerDao;
    private MenuItemDao menuItemDao;
    private RestaurantDao restaurantDao;

    public OrderDinnerServiceImpl(OrderDinnerDao orderDinnerDao, MenuItemDao menuItemDao, RestaurantDao restaurantDao) {
        this.orderDinnerDao = orderDinnerDao;
        this.menuItemDao = menuItemDao;
        this.restaurantDao = restaurantDao;
    }

    @Override
    public CommonResult updateOrderDinner(OrderDinner orderDinner) {
        Long restaurantId = orderDinner.getRestaurantId();
        if (null != restaurantId && null == restaurantDao.load(restaurantId)) {
            return CommonResult.fail("restaurantId 有误");
        }
        Long customerId = orderDinner.getCustomerId();
        Set<Long> customerIds = new HashSet<Long>() {{
            add(customerId);
        }};
        if (null != customerId && CollectionUtils.isEmpty(orderDinnerDao.queryUserCustomerInfo(customerIds))) {
            return CommonResult.fail("customerId 有误");
        }
        Integer verify=orderDinner.getVerify();
        if (verify!=null && verify==verify_confirm){
            orderDinner.setOrderStatus(order_completed);
        }
        return orderDinnerDao.updateOrderDinner(orderDinner) > 0 ?
                CommonResult.ok("更新订单成功") : CommonResult.fail("更新订单失败");
    }

    @Override
    public CommonResult<PageInfo<List<OrderDinnerVo>>> pageOrderDinner(Integer pageNum, Integer pageSize, String city, String area, String restaurantName,
                                                                       Time timeStart, Time timeEnd, Date orderTimeStart, Date orderTimeEnd, Integer orderStatus) {
        if (timeStart != null && timeEnd != null && timeStart.compareTo(timeEnd) > 0) {
            return CommonResult.fail("timeStart不能比timeEnd大");
        }
        if (orderTimeStart != null && orderTimeEnd != null && orderTimeStart.compareTo(orderTimeEnd) > 0) {
            return CommonResult.fail("orderTimeStart不能比orderTimeEnd大");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDinnerVo> orderDinners = orderDinnerDao.pageOrderDinner(city, area, restaurantName, timeStart, timeEnd, orderTimeStart, orderTimeEnd, orderStatus);
        return CommonResult.ok("查询首页图集详情列表成功", new PageInfo<OrderDinnerVo>(orderDinners));
    }

    @Override
    public CommonResult<List<OrderDinnerInfoVo>> listOrderDinner(Long restaurantId, Date startTime) {
        List<OrderDinnerInfoVo> orderDinnerInfoVos = orderDinnerDao.listOrderDinner(restaurantId, startTime);
        if (!CollectionUtils.isEmpty(orderDinnerInfoVos)) {
            Set<Long> customerIds = orderDinnerInfoVos.stream().map(OrderDinnerInfoVo::getCustomerId).collect(Collectors.toSet());
            List<UserCustomerInfoVo> userCustomerInfoVos = orderDinnerDao.queryUserCustomerInfo(customerIds);
            orderDinnerInfoVos.forEach(orderDinnerInfoVo -> {
                List<MenuItemInfoVo> menuItemInfoVos = menuItemDao.listMenuItemByOrderId(orderDinnerInfoVo.getId(), null, Constants.ONE);
                orderDinnerInfoVo.setMenuItemInfoVos(menuItemInfoVos);
                List<OrderMealInfoVo> orderMealInfoVos = menuItemDao.listOrderMealByOrderId(orderDinnerInfoVo.getId());
                orderDinnerInfoVo.setOrderMealInfoVos(orderMealInfoVos);
                userCustomerInfoVos.forEach(userCustomerInfoVo -> {
                    if (userCustomerInfoVo.getId().equals(orderDinnerInfoVo.getCustomerId())) {
                        orderDinnerInfoVo.setCustomerInfoVo(userCustomerInfoVo);
                    }
                });
            });
        }
        return CommonResult.ok("查询订单信息成功", orderDinnerInfoVos);
    }

    @Override
    public CommonResult<List<OrderMenuItemVo>> queryMenuItemByOrderId(Long orderId) {
        OrderMenuItemVo orderMenuItemVo = new OrderMenuItemVo();
        List<MenuItemInfoVo> menuItemInfoVos = menuItemDao.listMenuItemByOrderId(orderId, null, Constants.ONE);
        List<MealInfoVo> mealInfoVos = menuItemDao.listMealRelateByOrderId(orderId, Constants.TWO);
        mealInfoVos.forEach(mealInfoVo -> {
            List<MenuItemInfoVo> menuItemInfoVoList = menuItemDao.listMenuItemByOrderId(orderId, mealInfoVo.getSetMealId(), Constants.TWO);
            mealInfoVo.setItemInfoVoList(menuItemInfoVoList);
        });
        orderMenuItemVo.setMenuItemInfoVos(menuItemInfoVos);
        orderMenuItemVo.setMealInfoVos(mealInfoVos);
        return CommonResult.ok("查询订单下菜品信息成功", orderMenuItemVo);
    }

    @Override
    public CommonResult<List<OrderMealVo>> queryMenuItemByMealId(Long mealId) {
        return CommonResult.ok("查询订单下菜品信息成功", menuItemDao.queryMenuItemByMealId(mealId));
    }
}
