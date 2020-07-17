package com.example.demowx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demowx.domain.AccessTokenBean;
import com.example.demowx.domain.WxConfig;
import com.infoepcoh.cloud.common.util.RestTemplateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenService {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final Map<String, AccessTokenBean> tokenMap = new HashMap<>();

    @Resource
    private WxConfig wxConfig;

    /**
     * 发送网络请求获取token
     * @return
     */
    private void getAccessToken() {

        String formData = RestTemplateUtils.getFormData(String.format(ACCESS_TOKEN_URL,
                wxConfig.getAppID(),
                wxConfig.getAppSecret()));

        AccessTokenBean accessTokenBean = JSON.parseObject(formData, AccessTokenBean.class);

        // 设置token过期时间
        accessTokenBean.setExpires_in( System.currentTimeMillis() + (accessTokenBean.getExpires_in() * 1000) );

        tokenMap.put("token", accessTokenBean);

        System.out.println("获取的token....." + accessTokenBean.getAccess_token());
    }

    /**
     * 获取token
     * @return
     */
    public String getToken() {
        AccessTokenBean accessTokenBean = tokenMap.get("token");
        // token 不存在  ||  token 过期
        if (accessTokenBean == null || accessTokenBean.getExpires_in() < System.currentTimeMillis()) {
            // 重新获取
            this.getAccessToken();
        }

        accessTokenBean = tokenMap.get("token");

        return accessTokenBean.getAccess_token();
    }

}
