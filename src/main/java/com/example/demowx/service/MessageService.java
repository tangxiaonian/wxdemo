package com.example.demowx.service;

import com.example.demowx.domain.ArticleMessage;
import com.example.demowx.domain.BaseMessage;
import com.example.demowx.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    public BaseMessage handlerTextMessage(Map<String, String> requestMap, XStream xStream, BaseMessage baseMessage) {
        // 发生图文消息
        if (requestMap.get("Content").equals("图文")) {
            List<ArticleMessage.Article> articleList = new ArrayList<>();
            articleList.add(new ArticleMessage.Article("我爱中华", "这是一篇爱国文章",
                    "http://mmbiz.qpic.cn/mmbiz_jpg/fDvT06zD05t32icnk7UNy5HszGicTlGtGqm4ibaKWCfs0hjQFftjQMGsImiar6KqmwEtfwFegvh5FqCdFyvtJVGpZQ/0",
                    "http://www.baidu.com"));
            baseMessage = new ArticleMessage(1, articleList, requestMap);
            xStream.processAnnotations(ArticleMessage.class);
        }else {
            baseMessage = new TextMessage(requestMap, "消息以受理...");
            xStream.processAnnotations(TextMessage.class);
        }
        return baseMessage;
    }
}
