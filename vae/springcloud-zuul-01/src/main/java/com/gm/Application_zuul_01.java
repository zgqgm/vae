package com.gm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//生产者
@SpringBootApplication
//@EnableDiscoveryClient和@EnableEurekaClient共同点就是：都是能够让注册中心能够发现，扫描到改服务。
//@EnableEurekaClient//只适用于Eureka作为注册中心
@EnableZuulProxy // 开启Zuul
public class Application_zuul_01 {

    public static void main(String[] args) {
        SpringApplication.run(Application_zuul_01.class, args);
    }

}
