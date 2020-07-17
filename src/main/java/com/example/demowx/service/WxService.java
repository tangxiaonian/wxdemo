package com.example.demowx.service;

import com.example.demowx.domain.BaseMessage;
import com.example.demowx.domain.ImageMessage;
import com.example.demowx.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname WxService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/7/15 21:34
 * @Created by ASUS
 */
@Service
public class WxService {

    @Resource
    public MessageService messageServiceImpl;

    /**
     * 解析请求为map
     * @param inputStream
     * @return
     */
    public Map<String, String> requestMap(InputStream inputStream) {
        Map<String, String> infoMap = new HashMap<>();
        // 解析xml
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            // 根元素
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            elements.forEach(element -> {
                        infoMap.put(element.getName(), element.getStringValue());
                    }
            );
            return infoMap;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    /**
     * sha1加密
     * @param str_1
     * @param str_2
     * @return
     */
    public Boolean checkSignature(String str_1,String str_2) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            // 加密
            byte[] digest = messageDigest.digest(str_1.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f'};
            // 处理
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return str_2.equals(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 处理用户发送的消息
     * @param response
     * @param requestMap
     */
    public void responseMessage(HttpServletResponse response,
                                Map<String, String> requestMap) {
        // 解决消息乱码...
        response.setCharacterEncoding("UTF-8");

        // 获取消息的类型
        String msgType = requestMap.get("MsgType");

        System.out.println("消息类型为：" + msgType);

        BaseMessage baseMessage = null;

        XStream xStream = new XStream();

        switch (msgType) {
            case "text" :
                baseMessage = messageServiceImpl.handlerTextMessage(requestMap,xStream,baseMessage);
                break;
            case "image":
                baseMessage = null;
                baseMessage = messageServiceImpl.handlerImageMessage(requestMap,xStream,baseMessage);
                break;
        }
        responseMsg(response, baseMessage, xStream);
    }

    /**
     * 回复消息
     * @param response
     * @param baseMessage
     * @param xStream
     */
    private void responseMsg(HttpServletResponse response, BaseMessage baseMessage, XStream xStream) {
        response.setCharacterEncoding("UTF-8");
        // 回复数据
        try {
            PrintWriter printWriter = response.getWriter();
            if (baseMessage != null) {
                String xml = xStream.toXML(baseMessage);
                System.out.println("回复的内容为：---》\n" + xml);
                printWriter.print(xml);
            }else {
                printWriter.print("success");
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理事件消息
     * @param response
     * @param requestMap
     */
    public void responseEventMessage(HttpServletResponse response,
                                     Map<String, String> requestMap) {
        XStream xStream = new XStream();

        switch (requestMap.get("Event")) {
            case "subscribe":

                break;

            case "unsubscribe":

                break;

            case "CLICK":
                xStream.processAnnotations(TextMessage.class);
                responseMsg(response, new TextMessage(requestMap, "收到了你点击菜单的事件..."), xStream);
                break;
        }

    }

}