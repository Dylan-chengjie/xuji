package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@ApiModel(value = "OrderMenuItemVo", description = "订单信息")
public class OrderMenuItemVo {
    @ApiModelProperty(value = "菜品列表")
    private List<MenuItemInfoVo> menuItemInfoVos;
    @ApiModelProperty(value = "套餐列表")
    private List<MealInfoVo> mealInfoVos;
}
