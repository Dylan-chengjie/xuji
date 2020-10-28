package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;


@Data
@ApiModel(value = "OrderMealInfoVo", description = "套餐菜品信息")
public class OrderMealInfoVo {
    @ApiModelProperty(value = "套餐id")
    private String mealId;

    @ApiModelProperty(value = "套餐数量")
    private BigDecimal mealQuantity;

    @ApiModelProperty(value = "套餐内菜品列表")
    private List<MenuItemInfoVo> itemInfoVoList;
}
