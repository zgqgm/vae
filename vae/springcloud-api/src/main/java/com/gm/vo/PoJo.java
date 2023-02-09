package com.gm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)//链式编程
public class PoJo {
    private int id;

    private String message;
    //懒得写了
    //此处省略不定长行内容 (=´ω｀=)
}
