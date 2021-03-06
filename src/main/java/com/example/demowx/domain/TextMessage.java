package com.example.demowx.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @Classname TextMessage
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/7/15 23:36
 * @Created by ASUS
 */
@XStreamAlias("xml")
@Data
public class TextMessage extends BaseMessage{

    @XStreamAlias("Content")
    private String content;

    @XStreamAlias("MsgType")
    private String msgType;

    public TextMessage(Map<String, String> map,String content) {
        super(map);
        this.msgType = "text";
        this.content = content;
    }
}