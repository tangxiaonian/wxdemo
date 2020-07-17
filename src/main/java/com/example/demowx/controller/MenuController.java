package com.example.demowx.controller;

import com.example.demowx.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuServiceImpl;

    @GetMapping("/add")
    public String addClick() {
        return menuServiceImpl.addClick();
    }

}
