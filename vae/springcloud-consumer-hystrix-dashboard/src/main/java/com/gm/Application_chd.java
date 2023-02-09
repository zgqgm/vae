package com.gm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//流监视器
@SpringBootApplication
// 开启Dashboard
@EnableHystrixDashboard
public class Application_chd {

    public static void main(String[] args) {
        SpringApplication.run(Application_chd.class, args);
    }

}
