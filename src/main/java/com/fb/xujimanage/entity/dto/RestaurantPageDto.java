package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RestaurantPageDto {
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不能为空")
    private Integer pageNum=1;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize=10;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty(value = "门店名称")
    private String restaurantName;

    @ApiModelProperty(value = "门店状态（0 启用  ； 1 禁用）")
    private Integer status;
}
