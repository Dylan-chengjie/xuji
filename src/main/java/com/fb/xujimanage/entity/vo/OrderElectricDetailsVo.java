package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderElectricDetailsVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "菜品类型类型（1 菜品 ； 2 套餐）")
    private Integer itemType;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "菜品ID")
    private Long itemId;

    @ApiModelProperty(value = "菜品名称")
    private String itemName;

    @ApiModelProperty(value = "菜品单价")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "菜品数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "菜品总价")
    private BigDecimal countPrice;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "套餐ID")
    private Long mealId;

}
