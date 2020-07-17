package com.example.demowx.domain.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubButton extends BaseButton{

    private List<BaseButton> sub_button = new ArrayList<>();

    public SubButton(String name) {
        this.name = name;
    }

}
