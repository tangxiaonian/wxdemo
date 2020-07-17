package com.example.demowx.domain.button;

import lombok.Data;

@Data
public class ViewButton extends BaseButton{

    private String url;

    public String type;

    public ViewButton(String name, String url) {
        this.name = name;
        this.url = url;
        this.type = "view";
    }

}
