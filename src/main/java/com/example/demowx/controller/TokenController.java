package com.example.demowx.controller;

import com.example.demowx.service.AccessTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = {"/access"})
public class TokenController {

    @Resource
    private AccessTokenService accessTokenService;

    @GetMapping("/token")
    public String getToken() {
        return accessTokenService.getToken();
    }

}
