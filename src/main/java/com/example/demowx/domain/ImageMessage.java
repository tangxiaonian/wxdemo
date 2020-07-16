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
public class ImageMessage extends BaseMessage{

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("PicUrl")
    private String picUrl;

    @XStreamAlias("MediaId")
    private String mediaId;

    @XStreamAlias("MsgId")
    private String msgId;

    public ImageMessage(Map<String, String> map,
                        String picUrl,String mediaId,String msgId) {
        super(map);
        this.msgType = "image";
    }
}