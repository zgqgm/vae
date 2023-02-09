package com.gm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    /**
     * 获取token
     * @param claim token相关信息存储
     * @param salt 签名
     * @param time 过期时间（毫秒）
     * @return token字符串
     */
    public static String getToken(Map<String,Object> claim, String salt,Long time){
        //过期时间
        claim.put("exp",System.currentTimeMillis()+time);
        //当前时间
        claim.put("iat",System.currentTimeMillis());

        //未编码的头部
        String jsonHeader = "{\"alg\":\"HS256\"}";

        //编码后的头部
        String header = Base64.getEncoder().encodeToString(jsonHeader.getBytes(StandardCharsets.UTF_8));

        ObjectMapper mapper = new ObjectMapper();

        // 未编码的载荷
        String jsonPayload = null;

        try {
            jsonPayload = mapper.writeValueAsString(claim);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //编码后的载荷
        String payload = Base64.getEncoder().encodeToString(jsonPayload.getBytes(StandardCharsets.UTF_8));

        //获取十六进制md5加密后的签名
        String signature = DigestUtils.md5Hex(header+"."+payload+salt);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(header)
                .append(".")
                .append(payload)
                .append(".")
                .append(signature);
        return new String(stringBuilder);
    }
    //验证token
    public static Map<String,Object> verify(String token,String salt){
        Map<String, Object> map = new HashMap<>();

        String[] split = token.split("\\.");

        String payload = split[1];

        // 对载荷进行解码
        byte[] jsonPayload = Base64.getDecoder().decode(payload.getBytes(StandardCharsets.UTF_8));

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> claim = null;

        try {
            claim = objectMapper.readValue(jsonPayload, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取失效时间
        Long exp = (Long) claim.get("exp");

        // 如果当前时间已经超过了过期时间
        if (System.currentTimeMillis() >= exp) {
            map.put("message", "token已经失效");
            map.put("code", -1);
            map.put("success", false);

            return map;
        }

        // 获取新的签名
        String sign = DigestUtils.md5Hex(split[0] + "." + split[1] + salt);

        // 签名相同说明token没问题
        if (sign.equals(split[2])) {
            map.put("message", "token没问题");
            map.put("code", 200);
            map.put("success", true);
            map.put("data", claim);
            return map;
        }

        map.put("message", "token无法验证");
        map.put("code", -1);
        map.put("success", false);

        return map;
    }
}
