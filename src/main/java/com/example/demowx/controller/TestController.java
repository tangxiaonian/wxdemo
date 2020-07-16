package com.example.demowx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/bu")
    public String redirectBaidu() {
        return "redirect:http://www.baidu.com";
    }

}
