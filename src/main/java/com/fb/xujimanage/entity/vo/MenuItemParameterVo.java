package com.fb.xujimanage.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/18 11:04
 * @description:封装菜品所需的参数信息
 */
@Data
public class MenuItemParameterVo {
    /**
     * 菜品分类
     */
    private List<String> type;
    /**
     * 所属门店
     */
    private List<String> restaurant;

    /**
     * 销售单位
     */
    private List<String> unit;
}
