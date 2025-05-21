package com.atguigu.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
// 开启feign功能，扫描指定包下的接口，生成代理对象
@EnableFeignClients(basePackages = "com.atguigu.business.feign")
public class SeataBusinessMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataBusinessMainApplication.class, args);
    }
}
