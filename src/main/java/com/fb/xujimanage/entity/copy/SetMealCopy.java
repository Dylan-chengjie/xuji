package com.fb.xujimanage.entity.copy;

import lombok.Data;

import java.util.Date;

/**
 * 用于导数据转换套餐信息表------不要修改
 */
@Data
public class SetMealCopy {

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
     * 0 删除； 1 有效；
     */
    private int delFlag;

    /**
     * 修改时间
     */
    private Date updateTime;
}
