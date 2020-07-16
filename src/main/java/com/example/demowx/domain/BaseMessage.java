package com.example.demowx.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Classname BaseMessage
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/7/15 23:31
 * @Created by ASUS
 */
@Data
@XStreamAlias("xml")
public class BaseMessage implements Serializable {

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    public BaseMessage(Map<String, String> map) {

        this.toUserName = map.get("FromUserName");

        this.fromUserName = map.get("ToUserName");

        this.createTime = System.currentTimeMillis();
    }

}