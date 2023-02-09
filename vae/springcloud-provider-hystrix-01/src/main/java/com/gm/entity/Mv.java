package com.gm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@TableName("tb_mv")
@ApiModel(value = "Mv对象", description = "")
public class Mv implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("mv名字")
    @TableField("name")
    private String name;

    @ApiModelProperty("专辑名")
    @TableField("album_name")
    private String albumName;

    @ApiModelProperty("歌手")
    @TableField("artist_name")
    private String artistName;

    @ApiModelProperty("mv链接")
    @TableField("mv_uri")
    private String mvUri;

    @ApiModelProperty("mv图片")
    @TableField("mv_img")
    private String mvImg;

}
