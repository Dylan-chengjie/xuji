package com.fb.xujimanage.entity.copy;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于导数据转换菜品信息表------不要修改
 */
@Data
public class MenultemCopy {

    /**
     * id
     */
    private String id;

    /**
     * 菜品类别
     */
    private String type;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 销售单位
     */
    private String unit;

    /**
     *
     */
    private BigDecimal price;

    /**
     * 特色说明
     */
    private String featureDescribe;

    /**
     * 是否删除  0 有效  ； 1 删除
     */
    private Integer delFlag;

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
    private Integer sortNum;

    /**
     * 备注
     */
    private String remarks;

    /**
     *
     */
    private BigDecimal quantity;

    /**
     * 是否显示 0 不显示； 1  显示；
     */
    private Integer isShow;

    /**
     * 门店 code
     */
    private String restaurantCode;

    /**
     * 门店名称
     */
    private String restaurantName;

    /**
     * 菜品code
     */
    private String code;

    /**
     *
     */
    private BigDecimal memberPrice;

    /**
     * 大类编码
     */
    private String bigTypeCode;

    /**
     * 大类名称
     */
    private String bigTypeName;

    /**
     * 小类编码
     */
    private String smallTypeCode;

    /**
     * 小类名称
     */
    private String smallTypeName;

    /**
     * 1 已估清 ；2 具有库存；
     */
    private Integer haveKind;

    /**
     *
     */
    private BigDecimal memberPriceAlter;

    /**
     * 是否会员产品  1 是 ； 0 否；
     */
    private Integer memberProType;

    private  Date memPriceStart;


    private  Date memPriceEnd;
}
