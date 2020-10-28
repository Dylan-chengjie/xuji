package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
@ApiModel(value = "MenuItemInfoVo", description = "订单菜品信息")
public class MenuItemInfoVo {
    @ApiModelProperty(value = "菜品编码")
    private String itemCode;

    @ApiModelProperty(value = "菜品名称")
    private String itemName;

    @ApiModelProperty(value = "订单菜品数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "订单中菜品单价")
    private BigDecimal usePrice;

    @ApiModelProperty(value = "会员菜品价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "菜品类型 1单菜 2套餐")
    private String itemType;

    @ApiModelProperty(value = "套餐id")
    private String mealId;
}
