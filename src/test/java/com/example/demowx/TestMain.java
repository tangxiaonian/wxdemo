package com.example.demowx;

import com.example.demowx.domain.ArticleMessage;
import com.example.demowx.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

    @Test
    public void test01() {
        System.out.println(URLEncoder.encode("https://www.baidu.com"));
        System.out.println(URLEncoder.encode("http://wxhd.bj.189.cn/wa/connect/oauth2/authorize-redirect"));
    }

    @Test
    public void test02() {

        Map<String, String> map = new HashMap<>();
        map.put("FromUserName", "tang");
        map.put("ToUserName", "long");

        XStream xStream = new XStream();
        // 处理注解
        xStream.processAnnotations(ArticleMessage.class);

        List<ArticleMessage.Article> articleList = new ArrayList<>();

        articleList.add(new ArticleMessage.Article("我爱中华", "这是一篇爱国文章",
                "http://mmbiz.qpic.cn/mmbiz_jpg/fDvT06zD05t32icnk7UNy5HszGicTlGtGqm4ibaKWCfs0hjQFftjQMGsImiar6KqmwEtfwFegvh5FqCdFyvtJVGpZQ/0",
                "http://www.baidu.com"));

        ArticleMessage articleMessage = new ArticleMessage(0, articleList, map);

//        TextMessage textMessage = new TextMessage(map, "hello world!");

        String xml = xStream.toXML(articleMessage);

        System.out.println(xml);
    }

}
