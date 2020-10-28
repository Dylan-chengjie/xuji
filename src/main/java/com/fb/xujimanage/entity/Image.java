package com.fb.xujimanage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/21 7:36
 * @description:
 */
@Data
public class Image {
    /**
     * 主键
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String address;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 是否删除：0 不删，1删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
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
     * 排序号
     */
    private Integer sortNum;

    /**
     * 备注
     */
    private String remarks;
}
