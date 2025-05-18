package com.sky.product.service.impl;

import com.sky.product.bean.Product;
import com.sky.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {

        Product product = new Product();
        product.setId(productId);
        product.setProductName("苹果" + product.getId());
        product.setPrice(new BigDecimal("99"));
        product.setNum(2);

//        try {
//            TimeUnit.SECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return product;

    }
}
