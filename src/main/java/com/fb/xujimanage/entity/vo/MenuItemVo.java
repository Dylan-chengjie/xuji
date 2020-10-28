package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;


@Data
@ApiModel(value = "MenuItemVo", description = "菜品沽清")
public class MenuItemVo {

    @ApiModelProperty(value = "菜品ID")
    private  String id;

    @ApiModelProperty(value = "菜品编码")
    private String code;
    @ApiModelProperty(value = "菜品名称")
    private  String name;
    @ApiModelProperty(value = "估清类型")
    private  int haveKind;

    @ApiModelProperty(value = "库存剩余数量")
    private BigDecimal  quantity;
    @ApiModelProperty(value="门店ID")
    private  String restaurantCode;

    @ApiModelProperty(value="名称")
    private  String restaurantName;

}
