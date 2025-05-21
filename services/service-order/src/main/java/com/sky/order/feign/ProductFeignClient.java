package com.sky.order.feign;

import com.sky.order.feign.fallback.ProductFeignClientFallback;
import com.sky.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class) // feign客户端
public interface ProductFeignClient {

    // mvc注解的两套使用逻辑
    // 1. 标注在Controller，是接收这样的请求
    // 2. 标注在FeignClient上，是发送这样的请求
    @GetMapping("/api/product/product/{id}")
//    Product getProductById(@PathVariable("id") Long id,@RequestHeader("token") String token)
    Product getProductById(@PathVariable("id") Long id);

}
