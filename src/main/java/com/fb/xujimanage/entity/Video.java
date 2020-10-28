package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/17 14:12
 * @description:视频实体类
 */
@Data
public class Video implements Serializable {
    /**
     * id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 链接地址
     */
    private String url;

    /**
     * ''是否删除  0 有效  ； 1 删除'';
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
}
