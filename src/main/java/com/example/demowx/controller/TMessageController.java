package com.example.demowx.controller;

import com.example.demowx.service.TMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/industry")
public class TMessageController {

    @Resource
    public TMessageService tMessageServiceImpl;

    @PostMapping("set")
    private String setIndustry() {
        return tMessageServiceImpl.setIndustry();
    }

    @GetMapping("/get")
    public String getIndustry() {
        return tMessageServiceImpl.getIndustry();
    }

}
