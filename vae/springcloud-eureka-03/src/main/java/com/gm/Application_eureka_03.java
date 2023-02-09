package com.gm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//服务端的启动类，可以接受别人注册进来
public class Application_eureka_03 {

    public static void main(String[] args) {
        SpringApplication.run(Application_eureka_03.class, args);
    }

}
