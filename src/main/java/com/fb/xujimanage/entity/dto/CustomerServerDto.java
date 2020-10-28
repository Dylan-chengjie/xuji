package com.fb.xujimanage.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther Sam.yang
 * @date 2020/8/25
 * @description 客服信息Dto
 */
@Data
@ApiModel(value="CustomerServerDto",description="添加/更新 客服对象CustomerServerDto")
public class CustomerServerDto {

    @ApiModelProperty(value="主键id",name="id" ,required = false)
    private Long id;
    /**
     * 员工id ， 根据员工id获取员工姓名
     */
    @ApiModelProperty(value="员工编号",name="userNum" ,required = true)
    private String userNum;
    /**
     * 客服排序
     */
    @ApiModelProperty(value = "客服排序",name = "sortNum" ,required = true)
    private Integer sortNum;
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号",name = "wechat" ,required = true)
    private String wechat;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name = "contactWay" ,required = true)
    private String contactWay;
    /**
     * 管理门店id
     */
    @ApiModelProperty(value = "管理门店名称对应的门店编码",name = "restaurantId" ,required = true)
    private String restaurantCode;
}
