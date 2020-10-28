package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;


/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:49
 * @description:门店同菜单的关联实体类，多对多关系
 */
@Data
public class RestaurantMenuRelate implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 门店id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long restaurantId;

    /**
     * 菜单id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long menuId;
}
