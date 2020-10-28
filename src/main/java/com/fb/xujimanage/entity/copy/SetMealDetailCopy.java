package com.fb.xujimanage.entity.copy;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SetMealDetailCopy {
    /**
     * 套餐id
     */
    private String setMealId;

    /**
     * 类别
     */
    private String detailType;

    /**
     * 菜品id
     */
    private String dietId;

    /**
     * 菜品编码
     */
    private String dietCode;

    /**
     *
     */
    private BigDecimal detailQuantity;

    /**
     *
     */
    private BigDecimal addPrice;

    /**
     * 1默认选择 0替换菜品
     */
    private int defaultSelect;

    private Date updateTime;
    private String restaurantId;
}
