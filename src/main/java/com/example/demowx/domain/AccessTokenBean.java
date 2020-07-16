package com.example.demowx.domain;

import lombok.Data;

@Data
public class AccessTokenBean {

    private String access_token;

    private Long expires_in;
}
