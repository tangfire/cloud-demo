package com.sky.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            ServiceInstance choose = loadBalancerClient.choose("service-product");
            System.out.println("port: " + choose.getPort() + ", host: " + choose.getHost());
        }


    }

}
