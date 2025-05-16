package com.sky.order.service.impl;

import com.sky.order.bean.Order;
import com.sky.order.service.OrderService;
import com.sky.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemote(productId);
//        Product product = getProductFromRemoteWithLoadBalance(productId);
        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        BigDecimal totalAmount = product.getPrice().multiply(new BigDecimal(product.getNum()));
        order.setTotalAmount(totalAmount);
        order.setUserId(userId);
        order.setNickName("tangfire");
        order.setAddress("gdut");
        order.setProductList(Arrays.asList(product));
        return order;

    }

    private Product getProductFromRemote(Long productId){
        // 1. 获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance instance = instances.get(0);

        // 远程URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求路径: {}",url);
        // 2. 给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;

    }

    // 完成负载均衡发送请求
    private Product getProductFromRemoteWithLoadBalance(Long productId){
        // 1. 获取到商品服务所在的所有机器IP+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");

        // 远程URL
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程请求路径: {}",url);
        // 2. 给远程发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        return product;

    }

    // 基于注解的负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId){
        String url = "http://service-product/product/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

}
