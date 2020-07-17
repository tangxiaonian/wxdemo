package com.example.demowx.domain.button;

import lombok.Data;

@Data
public class ClickButton extends BaseButton{

    private String key;

    public String type;

    public ClickButton(String name, String key) {
        this.name = name;
        this.key = key;
        this.type = "click";
    }

}
