package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:34
 * @description:菜单实体类
 */
@Data
public class Menu implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单内容
     */
    private String content;

    /**
     * 适用口味 数据字典对应值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long tasteDicvalueid;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否删除  0 有效  ； 1 删除
     */
    private int delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 排序字段
     */
    private int sortNum;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 适用人数，数据字典值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long personDicvalueid;

    /**
     * 预算范围，数据字典值id
     */
    private String budgetId;
    /**
     * 门店集合
     */
    private List<Restaurant> restaurants;


}
