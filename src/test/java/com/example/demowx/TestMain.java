package com.example.demowx;

import com.alibaba.fastjson.JSON;
import com.example.demowx.domain.ArticleMessage;
import com.example.demowx.domain.button.BaseButton;
import com.example.demowx.domain.button.ButtonVo;
import com.example.demowx.domain.button.ClickButton;
import com.example.demowx.domain.button.ViewButton;
import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

    @Test
    public void test03() {
        ButtonVo buttonVo = new ButtonVo();
        List<BaseButton> button = buttonVo.getButton();
        button.add(new ClickButton("按钮1","key01"));
        button.add(new ViewButton("按钮2","http://www.baidu.com"));

        System.out.println(JSON.toJSON(buttonVo));

    }

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
