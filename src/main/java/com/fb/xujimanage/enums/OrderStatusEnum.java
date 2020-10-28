package com.fb.xujimanage.enums;

import lombok.Getter;
import lombok.Setter;

public enum OrderStatusEnum {
    UNPAID(0, "已提交"),
    PAID(1, "已完成"),
    CUSTOMER_CANCEL(2, "客户取消"),
    SYSTEM_CANCEL(3, "系统取消");

    @Getter
    @Setter
    private int type;

    @Getter
    @Setter
    private String describe;

    private OrderStatusEnum(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public static String getDescribe(int type) {
        for (OrderStatusEnum im : OrderStatusEnum.values()) {
            if (im.getType() == type) {
                return im.describe;
            }
        }
        return OrderStatusEnum.UNPAID.describe;
    }
}
