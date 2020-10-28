package com.fb.xujimanage.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/22 16:04
 * @description:查询门店参数接收对象
 */
@Data
@ApiModel(value="查询门店参数接收对象",description="查询门店参数接收对象RestaurantInfoResVo")
public class RestaurantInfoVo {
    /**
     * 门店
     */
    @ApiModelProperty(value="门店名",name="restaurantName" ,required = false)
    private String restaurantName;
    /**
     * 城市
     */
    @ApiModelProperty(value="城市",name="city" ,required = false)
    private String city;
    /**
     * 区域
     */
    @ApiModelProperty(value="区域",name="area" ,required = false)
    private String area;
}
