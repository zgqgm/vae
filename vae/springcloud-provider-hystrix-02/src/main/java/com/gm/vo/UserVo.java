package com.gm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVo {

    private String openId;

    private String name;

    private String userImg;

    private String phone;
}
