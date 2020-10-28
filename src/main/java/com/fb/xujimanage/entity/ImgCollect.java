package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author chengjie
 * @date 2020-08-13 10:59
 * @description:图片展示表实体
 * @version:
 */
@Data
@ApiModel(value = "ImgCollect", description = "图集实体")
public class ImgCollect extends BaseEntity {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "促销类型:0 Banner，1 精彩活动，2优惠特价")
    @Min(0)
    @Max(2)
    private Integer type;

    @ApiModelProperty(value = "促销名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否通版（0 非通版，1通版）")
    private Integer general;
}
