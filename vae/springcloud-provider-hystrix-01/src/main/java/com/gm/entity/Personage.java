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
@TableName("tb_personage")
@ApiModel(value = "Personage对象", description = "")
public class Personage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("中文名")
    @TableField("name")
    private String name;

    @ApiModelProperty("外国名")
    @TableField("foreignNames")
    private String foreignNames;

    @ApiModelProperty("别名")
    @TableField("alias")
    private String alias;

    @ApiModelProperty("标签（eg:音乐人等）")
    @TableField("label")
    private String label;

    @ApiModelProperty("国籍")
    @TableField("nationality")
    private String nationality;

    @ApiModelProperty("民族")
    @TableField("nation")
    private String nation;

    @ApiModelProperty("出生地")
    @TableField("birthland")
    private String birthland;

    @ApiModelProperty("出生日期")
    @TableField(" birthday")
    private LocalDate  birthday;

    @ApiModelProperty("星座")
    @TableField("constellation")
    private String constellation;

    @ApiModelProperty("血型")
    @TableField("RH")
    private String rh;

    @ApiModelProperty("身高")
    @TableField("height")
    private Float height;

    @ApiModelProperty("体重")
    @TableField("weight")
    private Float weight;

    @ApiModelProperty("毕业院校")
    @TableField("school")
    private String school;

    @ApiModelProperty("职业")
    @TableField("vocation")
    private String vocation;

    @ApiModelProperty("经纪公司")
    @TableField("brokerage")
    private String brokerage;


}
