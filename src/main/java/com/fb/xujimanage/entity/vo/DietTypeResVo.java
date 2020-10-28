package com.fb.xujimanage.entity.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author  summer.chou
 */
@Data
@ApiModel(value="菜品分类",description="DietTypeResVo")
public class DietTypeResVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键ID",name = "id")
    private long id;

    @ApiModelProperty(value = "门店编码",name = "restaurantCode")
    private String restaurantCode;


    @ApiModelProperty(value = "门店名称",name = "restaurantName")
    private String restaurantName;


    @ApiModelProperty(value = "菜品分类名称",name = "name")
    private String name;

    @ApiModelProperty(value = "菜品分类code",name = "code")
    private String code;


    @ApiModelProperty(value = "菜品分类排序",name = "sortNum")
    private Integer sortNum;

    @ApiModelProperty(value = "是否展示， 0 不展示；1 展示；",name = "isShow")
    private Integer isShow;

    @ApiModelProperty(value = "添加时间",name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "添加时间",name = "updateTime")
    private Date updateTime;

    @ApiModelProperty(value = "删除标记 0 有效  ； 1 删除",name = "delFlag")
    private Integer delFlag;

    @ApiModelProperty(value = "小类 0 ； 大类 1 ；",name = "bigOrSmall")
    private Integer bigOrSmall;

    @ApiModelProperty(value = "0 非实际称重计价  ； 1 按实际称重计价",name = "priceType")
    private Integer priceType;
}
