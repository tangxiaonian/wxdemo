package com.example.demowx;

import com.example.demowx.domain.WxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {WxConfig.class})
@SpringBootApplication
public class DemowxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemowxApplication.class, args);
    }

}
