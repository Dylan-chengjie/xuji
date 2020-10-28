package com.fb.xujimanage.util;


import com.fb.xujimanage.exception.VerifyException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * json公共返回结果
 */
@Data
@ApiModel(description = "返回响应数据")
public class CommonResult<E> implements Serializable {


    @ApiModelProperty(value = "响应业务状态")
    private Integer status=Renum.SUCCESS.getCode();
    @ApiModelProperty(value = "响应消息")
    private String msg="操作成功";
    @ApiModelProperty(value = "响应数据")
    private Object data;



    public static CommonResult build(Integer status, String msg, Object data) {
        return new CommonResult(status, msg, data);
    }

    public static CommonResult build(Integer status, String msg) {

        return new CommonResult(status, msg, null);
    }


    public static CommonResult ok() {
        return new CommonResult(null);
    }

    public static CommonResult ok(Object data) {
        return new CommonResult(data);
    }

    public static CommonResult ok(String msg) {

        return build(Renum.SUCCESS.getCode(), msg);
    }

    public static CommonResult ok(String msg, Object data) {
        return build(Renum.SUCCESS.getCode(), msg, data);
    }


    public static CommonResult fail() {
        return build(Renum.FAIL.getCode(),Renum.FAIL.getMsg());
    }

    public static CommonResult fail(Object data) {
        return build(Renum.FAIL.getCode(), Renum.FAIL.getMsg(), data);
    }

    public static CommonResult fail(String msg) {
        return build(Renum.FAIL.getCode(), msg);
    }

    public static CommonResult fail(String msg, Object data) {
        return build(Renum.FAIL.getCode(), msg, data);
    }

    public static void throwVerifyException(String msg) {
        throw new VerifyException(msg);
    }

    public CommonResult() {

    }

    public CommonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(Object data) {
        this.status = Renum.SUCCESS.getCode();
        this.msg =Renum.SUCCESS.getMsg();
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isOk() {
        return Renum.SUCCESS.getCode().equals(this.status);
    }


}

