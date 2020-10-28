package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 15:54
 * @description:门店同环境的关联实体类，多对多关系
 */
@Data
public class RestaurantEnvironmentRelate implements Serializable {
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
     * 环境id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long environmentId;
}
