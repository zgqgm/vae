package com.gm.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
//使用注释处理器生成自己的元数据
@ConfigurationProperties(prefix = "sender-email", ignoreUnknownFields = false)
@Data
public class MailUtil {
    private Map<String,MailProperties> mail;
    @Data
    public static class MailProperties {
        private String host;
        private String username;
        private String password;
        private String encoding;
        private String port;
        private String protocol;
    }
}
