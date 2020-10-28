package com.fb.xujimanage.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderElectricPageDto {
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不能为空")
    private Integer pageNum=1;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "门店名称/订单编号")
    private String nameOrOrderNo;

    @ApiModelProperty(value = "下单开始时间")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long orderTimeBegin;

    @ApiModelProperty(value = "下单结束时间")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long orderTimeEnd;
}
