package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chengjie
 * @version 1.0
 * @date 2020/9/4 14:27
 * @description:就餐信息实体类
 */
@Data
public class RestaurantDetailsVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "组织id")
    private Long orgId;

    @ApiModelProperty(value = "组织code")
    private String orgcode;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "组织类型")
    private String orgType;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "省编码")
    private String province;

    @ApiModelProperty(value = "城市编码")
    private String city;

    @ApiModelProperty(value = "门店编码")
    private String storecode;

    @ApiModelProperty(value = "主键")
    private BigDecimal longitude;

    @ApiModelProperty(value = "主键")
    private BigDecimal latitude;

    @ApiModelProperty(value = "门店地址")
    private String orgAddr;

    @ApiModelProperty(value = "是否在用 0 未用 ； 1 在用")
    private String valid;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;

    @ApiModelProperty(value = "vr 3d 的url")
    private String vrUrl;

    @ApiModelProperty(value = "门店图片id")
    private String imgId;

    @ApiModelProperty(value = "是否启用  0 启用  ； 1 禁用", name = "delFlag")
    private Integer delFlag;

    @ApiModelProperty(value = "联系方式")
    private String contactWay;
}
