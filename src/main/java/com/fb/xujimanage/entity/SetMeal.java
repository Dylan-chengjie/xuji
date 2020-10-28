package com.fb.xujimanage.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 套餐信息表
 */
@Data
public class SetMeal {

    /**
     * 套餐id
     */
    private String setId;

    /**
     * 菜品id
     */
    private String dietId;

    /**
     * 套餐编码
     */
    private String setCode;

    /**
     * 套餐名称
     */
    private String setName;

    /**
     * 开始日期
     */
    private Object startDate;

    /**
     * 结束日期
     */
    private Object endDate;

    /**
     * 门店id
     */
    private String restaurantId;

    /**
     * 套餐详情
     */
    private List<SetMealDetail> setMealDetails;

}
