package com.sky.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sky.order.bean.Order;
import com.sky.order.properties.OrderProperties;
import com.sky.order.service.OrderService;
import com.sky.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 激活配置中心修改了配置文件后自动刷新功能
//@RefreshScope
@Slf4j
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

//    @Value("${order.timeout}")
//    String orderTimeout;
//
//
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config(){
        return "order.timeout=" + orderProperties.getTimeout() + ",order.auto-confirm=" + orderProperties.getAutoConfirm() + ",order.db-url=" + orderProperties.getDbUrl();
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId, @RequestParam("productId") Long  productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;

    }

    // 秒杀创建订单
    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order",fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId",defaultValue = "888") Long userId, @RequestParam(value = "productId",defaultValue = "777") Long  productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;

    }


    public Order seckillFallback( Long userId,  Long  productId, Throwable exception) {
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("Error:"+exception.getClass());

        return order;

    }


    @GetMapping("writeDb")
    public String writeDb(){
        return "writeDb success...";
    }

    @GetMapping("/readDb")
    public String readDb(){
        log.info("readDb success...");
        return "readDb success...";
    }





}
