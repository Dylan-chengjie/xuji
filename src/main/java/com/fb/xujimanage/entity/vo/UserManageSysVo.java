package com.fb.xujimanage.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fb.xujimanage.util.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chengjie
 * @date 2020-08-21 17:59
 * @description:用户实体
 * @version:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@ApiModel(value = "UserManageSysVo", description = "用户实体")
public class UserManageSysVo implements Serializable {
    private static final long serialVersionUID = -4168226266366013867L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "员工工号")
    private String userNum;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "部门id")
    private String department;

    @ApiModelProperty(value = "组织id")
    private String orgId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "性别 118001 男 , 118002 女")
    private String gender;

    @ApiModelProperty(value = "120001-试用，-120002-在职，120003-离职")
    private String userStatus;

    @ApiModelProperty(value = "使用状态： 0 激活 ； 1 禁用")
    private Integer usageStatus;

    @ApiModelProperty(value = "100001-正式员工，100004-返聘工，100006-实习生，100010-非全日制工，100011-寒暑假工")
    private String userType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "令牌")
    private String token;

    @ApiModelProperty(value = "部门名称或者组织名称")
    private String restaurantName;

    @ApiModelProperty(value = "城市编码")
    private String city;

    @ApiModelProperty(value = "客服编号")
    private String wechat;

    @ApiModelProperty(value = "角色id集合")
    private List<Long> roleIds;
}
