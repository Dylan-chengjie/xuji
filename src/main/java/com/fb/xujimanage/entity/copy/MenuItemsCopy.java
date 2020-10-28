package com.fb.xujimanage.entity.copy;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "MenuItemVo", description = "菜品沽清")
public class MenuItemsCopy {

    @ApiModelProperty(value = "菜品ID")
    private String id;

    @ApiModelProperty(value = "估清类型")
    private  int haveKind;

    @ApiModelProperty(value = "库存剩余数量")
    private BigDecimal quantity;

    @ApiModelProperty(value="门店ID")
    private  String restaurantCode;



}
