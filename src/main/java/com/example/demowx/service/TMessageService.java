package com.example.demowx.service;

import com.alibaba.fastjson.JSON;
import com.example.demowx.domain.industry.IndustryParams;
import com.example.demowx.utils.HttpClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TMessageService {

    private static final String SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";

    private static final String GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";

    @Resource
    private AccessTokenService accessTokenServiceImpl;

    public String setIndustry() {

        String token = accessTokenServiceImpl.getToken();

        IndustryParams industryParams = new IndustryParams();
        industryParams.setIndustry_id1("1");
        industryParams.setIndustry_id1("4");

        try {
            return HttpClientUtils.postParameters(String.format(SET_INDUSTRY, token), JSON.toJSONString(industryParams));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success!";
    }

    public String getIndustry() {
        String token = accessTokenServiceImpl.getToken();
        try {
            return HttpClientUtils.get(String.format(GET_INDUSTRY, token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取成功!";

    }
}
