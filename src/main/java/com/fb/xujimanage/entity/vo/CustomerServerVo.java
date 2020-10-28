package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

/**
 * 客服信息Vo
 *
 * @author Sam.yang
 * @since 2020-08-24 17:07:51
 */
@Data
public class CustomerServerVo   {
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;
    /**
     * 客户名称
     */
    private String customerServerName;
    /**
     * 客服排序
     */
    private Integer sortNum;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 联系电话
     */
    private String contactWay;
    /**
     * 管理门店城市
     */
    private String city;

    /**
     * 管理门店区域
     */
    private String area;

    /**
     * 管理门店地址
     */
    private String address;

    /**
     * 管理门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long restaurantId;

    private String restaurantCode;

    private String gender;

    private  String department;

    private  String  userNum;

    private  String restaurantName;



}