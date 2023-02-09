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
@TableName("tb_music")
@ApiModel(value = "Music对象", description = "")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "music_id", type = IdType.AUTO)
    private Long musicId;

    @TableField("music_name")
    private String musicName;

    @TableField("music_album_name")
    private String musicAlbumName;

    @TableField("music_album_pic_url")
    private String musicAlbumPicUrl;

    @TableField("music_mp3_url")
    private String musicMp3Url;

    @TableField("music_artist_name")
    private String musicArtistName;

    @TableField("owner")
    private Long owner;


}
