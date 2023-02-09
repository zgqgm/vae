package com.gm.entity.entity_2;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_userinfo")
@ApiModel(value = "userinfo对象", description = "")
@Data
@Accessors(chain = true)//链式编程
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("openId")
    private String openId;

    @ApiModelProperty("用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty("头像")
    @TableField("userImg")
    private String userImg;

    @ApiModelProperty("用户手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty("用户ip")
    @TableField("userIp")
    private String userIp;

    @ApiModelProperty("用户性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("用户年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("个性签名")
    @TableField("motto")
    private String motto;

    @ApiModelProperty("等级")
    @TableField("lv")
    private Integer lv;

    @ApiModelProperty("用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("签名")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("token")
    @TableField("token")
    private String token;

    @ApiModelProperty("用户登录时间")
    @TableField(value = "loginTime")
    private Date loginTime;

    @ApiModelProperty("错误时间")
    @TableField(value = "errorTime")
    private Date errorTime;

    @ApiModelProperty("尝试次数")
    @TableField(value = "nums")
    private Integer nums;

    @ApiModelProperty("该账号登录失败的ip日志id")
    @TableField(value = "errorIpId")
    private Integer errorIpId;
}

