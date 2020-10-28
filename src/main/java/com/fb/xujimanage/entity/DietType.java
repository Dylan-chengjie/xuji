package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜品类别表(DietType)实体类
 *
 * @author makejava
 * @since 2020-08-28 17:53:48
 */
@Data
public class DietType  {

    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

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

    /**
     *
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long createBy;

    /**
     *
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long updateBy;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 删除标记 0 有效  ； 1 删除
     */
    private Integer delFlag;

    /**
     * 小类 0 ； 大类 1 ；
     */
    private Integer bigOrSmall;

    /**
     * 0 非实际称重计价  ； 1 按实际称重计价；
     */
    private Integer priceType;

    /**
     * 菜品对象
     */
    private List<MenuItem> menuItems;




}
