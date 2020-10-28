package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @description:服务特色
 * @date 2020/8/18 20:34
 */
@Data
public class FeatureService implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 服务特色名称
     */
    private String name;

    /**
     * 数据字典值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long sceneDicvalueId;

    /**
     * 服务特色说明
     */
    private String serviceExplain;

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
     * 备注
     */
    private String remarks;
    /**
     * 门店实体
     */
    private Restaurant restaurant;

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
}
