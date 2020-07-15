package com.example.demowx.domain;

import lombok.Data;

import java.util.Map;

/**
 * @Classname TextMessage
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/7/15 23:36
 * @Created by ASUS
 */
@Data
public class TextMessage extends BaseMessage{

    private String content;

    private String msgType;

    public TextMessage(Map<String, String> map,String content) {
        this.msgType = "text";
        this.content = content;
    }
}