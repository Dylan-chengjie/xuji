package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.junit.Test;

import java.util.List;

@Data
public class RestaurantsVo {


    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键ID",name = "id")
    private long id;


    /**
     * 名称
     */
    @ApiModelProperty(value = "名称",name = "restaurantName")
    private String restaurantName;

    /**
     * 电子门店地址
     */
    @ApiModelProperty(value = "电子门店地址",name = "address")
    private String address;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市",name = "city")
    private String city;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式",name = "contactWay")
    private String contactWay;
    /**
     * 区域
     */
    @ApiModelProperty(value = "区域",name = "area")
    private String area;

    /**
     * 门店图片对应的图片地址
     */
    @ApiModelProperty(value = "门店图片对应的图片地址",name = "imgUrl")
    private String imgUrl;

    /**
     * vr 3d 的url
     */
    @ApiModelProperty(value = "vr 3d 的url",name = "vrUrl")
    private String vrUrl;

    @ApiModelProperty(value = "门店code",name = "code")
    private  String code;
    @ApiModelProperty(value = "父ID",name = "parentId")
    private  String parentId;


    @ApiModelProperty(value = " 组织ID",name = "orgId")
    private  String orgId;

    @ApiModelProperty(value = "组织类型  dept部门,store门店,steam小组,area区域",name = "orgType")
    private String orgType;
    //无线级分类
    List<RestaurantsVo> children;
}
