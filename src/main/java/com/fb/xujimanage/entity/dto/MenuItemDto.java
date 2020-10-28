package com.fb.xujimanage.entity.dto;

import com.fb.xujimanage.entity.Image;
import com.fb.xujimanage.entity.Restaurant;
import com.fb.xujimanage.entity.Video;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:48
 * @description:菜品信息实体类
 */
@Data
@ApiModel(value = "MenuItemDto", description = "菜品信息")
public class MenuItemDto implements Serializable {


    @ApiModelProperty(value = "菜品ID")
    private  int id;

    @ApiModelProperty(value = "菜品编码")
    private String code;
    @ApiModelProperty(value = "菜品名称")
    private  String name;
    @ApiModelProperty(value = "估清类型")
    private  int haveKind;

    @ApiModelProperty(value = "库存剩余数量")
    private int  quantity;
    @ApiModelProperty(value="门店ID")
    private  String restaurantCode;

    @ApiModelProperty(value="名称")
    private  String restaurantName;




}
