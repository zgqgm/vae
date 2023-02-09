package com.gm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName(value = "tb_iplog")
@ApiModel(value = "记录非法ip日志")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpLog {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("非法ip")
    @TableField(value = "errorIp")
    private String errorIp;

    @ApiModelProperty("错误时间")
    @TableField(value = "errorTime")
    private Date errorTime;

    @ApiModelProperty("尝试次数")
    @TableField(value = "nums")
    private Integer nums;
}
