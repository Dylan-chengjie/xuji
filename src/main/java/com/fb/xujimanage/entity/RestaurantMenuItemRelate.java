package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/21 8:01
 * @description:
 */
@Data
public class RestaurantMenuItemRelate implements Serializable {
    /**
     * 主键id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long restaurantId;

    /**
     * 菜品id
     */
    private String menuItemId;

}
