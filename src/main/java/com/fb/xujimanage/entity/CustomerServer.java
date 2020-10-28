package com.fb.xujimanage.entity;

import lombok.Data;

/**
 * 客服信息表(CustomerServer)实体类
 *
 * @author sam.yang
 * @since 2020-08-24 17:07:51
 */
@Data
public class CustomerServer extends BaseEntity  {
    private static final long serialVersionUID = -89602827077742121L;

    private Long id;
    /**
     * 员工id ， 根据员工id获取员工姓名
     */
    private String userNum;
    /**
     * 客服排序
     */
    private Integer sortNum;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 联系电话
     */
    private String contactWay;
    /**
     * 管理门店id
     */
   // private Long restaurantId;

    private String restaurantCode;

    private String gender;

    private  String department;


}
