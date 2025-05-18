package com.sky.order;


import com.sky.order.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {
    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    public void testWeather() {
        String response = weatherFeignClient.getWeather("511025",
                "资中", "202503",
                "20250301", "20250303",
                 "APPCODE " + "451413c94ad84fef98a46e61f41359c6");

        System.out.println(response);
    }
}