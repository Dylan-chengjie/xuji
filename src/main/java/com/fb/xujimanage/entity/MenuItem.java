package com.fb.xujimanage.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:48
 * @description:菜品信息实体类
 */
@Data
public class MenuItem implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 菜品类型 1单菜 2套餐
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
     *
     */
    private BigDecimal quantity;

    /**
     * 是否显示 0 显示； 1 不显示；
     */
    private int isShow;

    /**
     * 门店 code （无效字段 ）关联表restaurant_menu_item_relate
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
     * 1 已估清 ；2 具有库存；3 不限售
     */
    private int haveKind;

    /**
     *
     */
    private BigDecimal memberPriceAlter;

    /**
     * 是否会员产品  1 是 ； 0 否；
     */
    private int memberProType;

    /**
     * 会员价 开始时间
     */
    private Date memPriceStart;

    /**
     * 会员价 结束时间
     */
    private Date memPriceEnd;


    /**
     * 菜品分类
     * (非数菜品表字段)
     */
    private String classifyName;
    /**
     * 0 非实际称重计价  ； 1 按实际称重计价；
     * (非数菜品表字段)
     */
    private int priceType;
    /**
     * 分类code
     */
    private String dietCode;
    /**
     * 分类名字
     */
    private String dietName;

    /**
     * 门店集合
     */
    private List<Restaurant> restaurantList;
    /**
     * 图片集合
     */
    private List<Image> imageList;

    /**
     * 视频集合
     */
    private List<Video> videoList;
    /**
     * 套餐对象
     */
    private SetMeal setMeal;

    @Transient
    private Integer dtPriceType;
}
