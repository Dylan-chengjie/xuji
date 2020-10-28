package com.fb.xujimanage.entity.vo;

import com.fb.xujimanage.entity.OrderDinner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chengjie
 * @date 2020-08-21 17:59
 * @description:预点订单响应实体
 * @version:
 */
@Data
@ApiModel(value = "OrderDinnerVo", description = "预点订单响应实体")
public class OrderDinnerVo extends OrderDinner {
    @ApiModelProperty(value = "会员号")
    private String cno;

    @ApiModelProperty(value = "用户名")
    private String customerName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "门店名称")
    private String restaurantName;

    @ApiModelProperty(value = "电子门店地址")
    private String address;

    @ApiModelProperty(value = "核对状态  0 未读，1已读")
    private Integer checkStatus;
}
