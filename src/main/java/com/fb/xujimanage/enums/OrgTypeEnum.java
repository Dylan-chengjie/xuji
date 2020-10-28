package com.fb.xujimanage.enums;

import lombok.Getter;
import lombok.Setter;

public enum OrgTypeEnum {
    DEPT("dept", "部门"),
    STORE("store", "门店"),
    STEAM("steam", "小组"),
    AREA("area", "区域");

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String describe;

    private OrgTypeEnum(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public static String getDescribe(String type) {
        for (OrgTypeEnum im : OrgTypeEnum.values()) {
            if (im.getType() == type) {
                return im.describe;
            }
        }
        return OrgTypeEnum.STORE.describe;
    }
}
