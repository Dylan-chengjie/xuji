package com.fb.xujimanage.enums;

import lombok.Getter;
import lombok.Setter;

public enum ImgCollectEnum {
    HOME_BANNER(0, "首页广告"),
    SPLENDID_EVENT(1, "精彩活动"),
    SPECIAL_PRICE(2, "优惠特价");

    @Getter
    @Setter
    private int type;

    @Getter
    @Setter
    private String describe;

    private ImgCollectEnum(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public static String getDescribe(int type) {
        for (ImgCollectEnum im : ImgCollectEnum.values()) {
            if (im.getType() == type) {
                return im.describe;
            }
        }
        return ImgCollectEnum.HOME_BANNER.describe;
    }
}
