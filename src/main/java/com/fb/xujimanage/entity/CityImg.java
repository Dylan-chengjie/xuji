package com.fb.xujimanage.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CityImg implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 图片id
     */
    private String imgId;

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
}
