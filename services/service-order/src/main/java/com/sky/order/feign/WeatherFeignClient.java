package com.sky.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client", url = "https://ali-weather.showapi.com")
public interface WeatherFeignClient {

    @GetMapping("/weatherhistory")
    String getWeather(@RequestParam("areCode") String areaCode,
                    @RequestParam("area") String area,
                    @RequestParam("month") String month,
                    @RequestParam("startDate") String startDate,
                    @RequestParam("endDate") String endDate,
                    @RequestHeader("Authorization") String appCode);
}