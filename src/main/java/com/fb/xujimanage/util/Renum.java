package com.fb.xujimanage.util;

public enum Renum {
    SUCCESS(200, "成功"),
    FAIL(201,"失败"),
    DATA_IS_NULL(202,"token为null"),
    MULTIPLE_COMPARISON(205,"无法进行多次比较"),
    USERSTATUS_ISNOLOGIN(203,"token失效需要重新登录"),
    PARAMETER_IS_NULL(601,"参数为空"),
    LINK_FAILURE(602,"链接失效")
    ;
    private Integer code;
    private String msg;

    Renum(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {

        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
