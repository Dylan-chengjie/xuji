package com.fb.xujimanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.service.FirstGroup;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-21 17:59
 * @description:管理后台用户实体
 * @version:
 */
@Data
@ApiModel(value = "UserManageSys", description = "管理后台用户实体")
public class UserManageSys {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @NotEmpty(message = "userNum 不能为空", groups = {FirstGroup.class})
    @ApiModelProperty(value = "员工工号")
    private String userNum;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "部门id")
    private String department;

    @ApiModelProperty(value = "组织id")
    private String orgId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "性别 118001 男 , 118002 女")
    private String gender;

    @ApiModelProperty(value = "120001-试用，-120002-在职，120003-离职")
    private String userStatus;

    @ApiModelProperty(value = "使用状态： 0 激活 ； 1 禁用")
    private Integer usageStatus;

    @ApiModelProperty(value = "100001-正式员工，100004-返聘工，100006-实习生，100010-非全日制工，100011-寒暑假工")
    private String userType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Transient
    @ApiModelProperty(value = "门店CODE集合")
    private List<String> restaurantCodes;
}
