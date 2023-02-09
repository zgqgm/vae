package com.gm.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//RestTemplate并没有注入到Spring中，所以才需要我们自己编写配置类注册到Spring中。
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced//实现负载均衡-ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
