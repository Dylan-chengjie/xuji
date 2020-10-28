package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@ApiModel(value = "MealInfoVo", description = "套餐菜品信息")
public class MealInfoVo {
    @ApiModelProperty(value = "套餐id")
    private String setMealId;

    @ApiModelProperty(value = "套餐名称")
    private String setName;

    @ApiModelProperty(value = "订单中菜品单价")
    private BigDecimal usePrice;

    @ApiModelProperty(value = "订单套餐数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "会员菜品价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "菜品类型 1单菜 2套餐")
    private String itemType;

    @ApiModelProperty(value = "套餐内菜品列表")
    private List<MenuItemInfoVo> itemInfoVoList;
}
