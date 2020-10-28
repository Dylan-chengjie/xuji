package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
@ApiModel(value = "OrderMealVo", description = "套餐菜品信息")
public class OrderMealVo {
    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "套餐id")
    private String mealId;

    @ApiModelProperty(value = "菜品数量")
    private BigDecimal dietQuantity;

    @ApiModelProperty(value = "菜品名称")
    private String itemName;
}
