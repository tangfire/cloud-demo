package com.sky.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order") // 配置批量绑定在nacos中，可以无需@RefreshScope就能实现自动刷新
@Data
public class OrderProperties {


    String timeout;


    String autoConfirm;

    String dbUrl;
}
