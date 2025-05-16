package com.sky.product.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int num;
}
