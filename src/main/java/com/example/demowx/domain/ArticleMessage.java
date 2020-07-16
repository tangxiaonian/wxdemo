package com.example.demowx.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;
import java.util.Map;

@XStreamAlias("xml")
@Data
public class ArticleMessage extends BaseMessage{

    @XStreamAlias("ArticleCount")
    private Integer articleCount;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Articles")
    private List<Article> articles;

    public ArticleMessage(Integer articleCount,
                          List<ArticleMessage.Article> articles,
                          Map<String,String> requestMap) {
        super(requestMap);
        this.articleCount = articleCount;
        this.msgType = "news";
        this.articles = articles;
    }

    @XStreamAlias("item")
    @Data
    public static class Article {

        @XStreamAlias("Title")
        private String title;

        @XStreamAlias("Description")
        private String description;

        @XStreamAlias("PicUrl")
        private String picUrl;

        @XStreamAlias("Url")
        private String url;

        public Article(String title,String description,String picUrl,String url) {
            this.title = title;
            this.description = description;
            this.picUrl = picUrl;
            this.url = url;
        }

    }

}
