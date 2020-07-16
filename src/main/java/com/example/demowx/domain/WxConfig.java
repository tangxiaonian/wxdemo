package com.example.demowx.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    private String appID;

    private String appSecret;

}
