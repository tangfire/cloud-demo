package com.sky.order.service;


import com.sky.order.bean.Order;

public interface OrderService {
    public Order createOrder(Long productId,Long userId);
}
