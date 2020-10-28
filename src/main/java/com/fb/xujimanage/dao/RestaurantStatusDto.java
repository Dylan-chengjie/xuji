package com.fb.xujimanage.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RestaurantStatusDto {
    @ApiModelProperty(value = "门店ID")
    private Long id;
    @ApiModelProperty(value = "门店状态（0 启用  ； 1 禁用）")
    private Integer status;
}
