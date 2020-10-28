package com.fb.xujimanage.entity.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("DietTypeUpdateDto")
public class DietTypeUpdateDto {

    @ApiModelProperty(value = "主键ID",name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;


    @ApiModelProperty(value = "菜品分类排序",name = "sortNum")
    private Integer sortNum;

    @ApiModelProperty(value = "是否展示， 0 不展示；1 展示；",name = "isShow")
    private Integer isShow;


    @ApiModelProperty(value = "0 非实际称重计价  ； 1 按实际称重计价；",name = "priceType")
    private Integer priceType;
}
