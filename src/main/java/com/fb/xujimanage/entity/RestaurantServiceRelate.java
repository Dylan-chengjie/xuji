package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaw
 * @description: 门店同服务特色的关联表，多对多关系
 * @date xiaw modify on 2020/8/2319:51
 */
@Data
public class RestaurantServiceRelate implements Serializable {
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
     * 服务特色id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long serviceId;

}
