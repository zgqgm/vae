package com.gm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
/**
 *vo视图
 * @param <T> 泛型
 */
public class ResultVO<T> {

    //存储数据
    private T data;

    //信息
    private String message;

    //状态码
    private Integer code;

    //动态数据
    private Map<String, Object> map = new HashMap<>();

    public static <T> ResultVO<T> success(T object) {
        ResultVO<T> r = new ResultVO<>();
        r.data = object;
        r.code = 666;
        return r;
    }

    public static <T> ResultVO<T> error(String msg) {
        ResultVO<T> r = new ResultVO<>();
        r.message = msg;
        r.code = 444;
        return r;
    }

    public ResultVO<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
