package com.sky.order.controller;

import com.sky.order.bean.Order;
import com.sky.order.service.OrderService;
import com.sky.product.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 激活配置中心修改了配置文件后自动刷新功能
@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Value("${order.timeout}")
    String orderTimeout;


    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/config")
    public String config(){
        return "order.timeout=" + orderTimeout + ",order.auto-confirm=" + orderAutoConfirm;
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long  productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;

    }



}
