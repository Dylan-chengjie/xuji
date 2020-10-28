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
 * @date 2020-08-26 10:59
 * @description:图集与图片关联实体
 * @version:
 */
@Data
@ApiModel(value = "ImgCollectRelate", description = "图集与图片关联实体")
public class ImgCollectRelate {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "图片id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long imgId;

    @ApiModelProperty(value = "图集id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long collectId;
}
