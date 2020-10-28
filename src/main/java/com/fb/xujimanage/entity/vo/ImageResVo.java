package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/23 2:49
 * @description:封装图片返回信息
 */
@Data
@ApiModel(value = "封装图片返回信息", description = "封装图片返回信息:ImageResVo")
public class ImageResVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "图片id", name = "id", required = false)
    private long id;
    @ApiModelProperty(value = "图片地址", name = "address", required = false)
    private String address;
}
