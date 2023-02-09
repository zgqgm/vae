package com.gm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-07-23
 */
@Getter
@Setter
@TableName("tb_prize")
@ApiModel(value = "Prize对象", description = "")
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("奖项类别")
    @TableField("type")
    private String type;

    @ApiModelProperty("获奖时间")
    @TableField("time")
    private LocalDate time;

    @ApiModelProperty("奖项名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("获奖作品")
    @TableField("opus")
    private String opus;

    @TableField("owner")
    private Long owner;


}
