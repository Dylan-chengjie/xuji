package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @auther Sam.yang
 * @date 2020/8/25
 * @description
 */
@Data
public class RestaurantVo {

    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键ID", name = "id")
    private long id;


    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "restaurantName")
    private String restaurantName;

    /**
     * 电子门店地址
     */
    @ApiModelProperty(value = "电子门店地址", name = "address")
    private String address;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市", name = "city")
    private String city;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", name = "contactWay")
    private String contactWay;

    @ApiModelProperty(value = "是否启用  0 启用  ； 1 禁用", name = "delFlag")
    private Integer delFlag;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域", name = "area")
    private String area;

    /**
     * vr 3d 的url
     */
    @ApiModelProperty(value = "vr 3d 的url", name = "vrUrl")
    private String vrUrl;

    @ApiModelProperty(value = "门店code", name = "code")
    private String code;

    @ApiModelProperty(value = "父ID", name = "parentId")
    private String parentId;


    @ApiModelProperty(value = " 组织ID", name = "vrUrl")
    private String orgId;

    @ApiModelProperty(value = "组织类型  dept部门,store门店,steam小组,area区域", name = "orgType")
    private String orgType;

    @ApiModelProperty(value = "门店图片id")
    private String imgId;

    @ApiModelProperty(value = "城市、地址等是否在后台进行了修改 0 未修改；1 修改")
    private Integer modifyFlag;

}
