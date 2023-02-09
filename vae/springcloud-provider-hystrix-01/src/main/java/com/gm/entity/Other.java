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
@TableName("tb_other")
@ApiModel(value = "Other对象", description = "")
public class Other implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("作品")
    @TableField("opus")
    private String opus;

    @ApiModelProperty("封面")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("链接")
    @TableField("link")
    private String link;

    @ApiModelProperty("为谁创作")
    @TableField("name")
    private String name;

    @TableField("owner")
    private Long owner;


}
