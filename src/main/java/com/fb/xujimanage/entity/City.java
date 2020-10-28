package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Data
public class City {

    /**
     * 城市编号
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    /**
     * 省份编号
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;


}
