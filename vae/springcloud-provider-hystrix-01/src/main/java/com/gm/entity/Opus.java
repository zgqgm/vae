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
@TableName("tb_opus")
@ApiModel(value = "Opus对象", description = "")
public class Opus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("作品")
    @TableField("opus")
    private Integer opus;

    @ApiModelProperty("链接")
    @TableField("link")
    private Integer link;

    @ApiModelProperty("权重")
    @TableField("weight")
    private Integer weight;

    @TableField("owner")
    private Long owner;


}
