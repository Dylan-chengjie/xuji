package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 17:54
 * @description:门店信息返回对象
 **/
@Data
@ApiModel(value="门店信息返回对象",description="门店信息返回对象RestaurantInfoResVo")
public class RestaurantInfoResVo extends RestaurantInfoVo {
    /**
     * 门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value="门店id",name="id" ,required = true)
    private Long id;
    /**
     * 门店地址
     */
    @ApiModelProperty(value="门店地址",name="address" ,required = true)
    private String address;

}
