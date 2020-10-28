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
 * @date 2020/8/17 15:43
 * @description:环境配置信息实体类
 */
@Data
public class Environment implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 环境布置名称
     */
    private String name;

    /**
     * 数据字典值id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long sceneDicvalueId;

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
     * 总数
     */
    private int total;


}
