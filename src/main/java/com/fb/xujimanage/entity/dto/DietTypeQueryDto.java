package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @auther Sam.yang
 * @date 2020/8/31
 * @description
 */
@Data
@ApiModel("DietTypeQueryDto")
public class DietTypeQueryDto extends BaseDto{

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市编码",name = "city")
    private String city;


    @ApiModelProperty(value = "门店编码",name = "restaurantCode")
    private  String restaurantCode;

    /**
     * 菜品分类编码列表
     */
    @ApiModelProperty(value = "门店code",name = "restaurantCode")
    private List<String> codeList;
}
