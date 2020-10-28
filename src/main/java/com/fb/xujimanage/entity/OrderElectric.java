package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  电子订单
 *
 */
@Data
public class OrderElectric implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
     *
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 就餐人数
     */
    private int personCount;

    /**
     * 顾客需求
     */
    private String customerRequire;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 餐桌编号
     */
    private String deskNum;

    /**
     * 订单状态 0  未完成； 1 已完成；
     */
    private int status;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 顾客用户id
     */
    private long userCusId;

    /**
     * 门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long restaurantId;



}
