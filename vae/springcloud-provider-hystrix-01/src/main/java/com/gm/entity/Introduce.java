package com.gm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("tb_introduce")
@ApiModel(value = "Introduce对象", description = "")
public class Introduce implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("基本信息")
    @TableField("information")
    private String information;

    @ApiModelProperty("主要成就")
    @TableField("accomplishment")
    private String accomplishment;

    @ApiModelProperty("早年经历")
    @TableField("experienced")
    private String experienced;

    @ApiModelProperty("演艺经历")
    @TableField("acting")
    private String acting;

    @ApiModelProperty("演唱会记录")
    @TableField("concert")
    private String concert;

    @ApiModelProperty("人物评价")
    @TableField("appraise")
    private String appraise;


}
