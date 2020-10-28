package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderElectricPageVo {

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "订单ID")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "会员电话")
    private String userTel;

    @ApiModelProperty(value = "会员ID")
    private String userId;

    @ApiModelProperty(value = "会员名字")
    private String userName;

    @ApiModelProperty(value = "下单门店名称")
    private String restaurantName;

    @ApiModelProperty(value = "城市编码")
    private String city;

    @ApiModelProperty(value = "电子门店地址")
    private String address;

    @ApiModelProperty(value = "下单门店code")
    private String restaurantCode;

    @ApiModelProperty(value = "点单金额（元）")
    private BigDecimal amountMoney;

    @ApiModelProperty(value = "就餐人数")
    private String personCount;

    @ApiModelProperty(value = "餐桌号")
    private String deskNum;

    @ApiModelProperty(value = "下单时间")
    private String createTime;

    @ApiModelProperty(value = "订单备注")
    private String orderRemark;

    @ApiModelProperty(value = "订单状态 0  未完成； 1 已完成")
    private Integer status;
}
