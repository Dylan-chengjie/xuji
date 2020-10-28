package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

/**
 * @auther Sam.yang
 * @date 2020/8/31
 * @description 菜品类别Vo
 */
@Data
public class DietTypeVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;
    /**
     * 门店编码
     */
    private String restaurantCode;
    /**
     * 门店名称
     */
    private String restaurantName;
    /**
     * 菜品分类名称
     */
    private String name;
    /**
     * 菜品分类code
     */
    private String code;
    /**
     * 菜品分类排序
     */
    private Integer sortNum;
    /**
     * 是否展示， 0 不展示；1 展示；
     */
    private Integer isShow;

    private String city;

    private int priceType;

}
