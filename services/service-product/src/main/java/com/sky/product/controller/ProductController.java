package com.sky.product.controller;

import com.sky.product.bean.Product;
import com.sky.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId, HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println("hello..... token:" + header);
        Product product =  productService.getProductById(productId);
        return product;
    }
}
