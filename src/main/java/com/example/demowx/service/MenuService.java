package com.example.demowx.service;

import com.alibaba.fastjson.JSON;
import com.example.demowx.domain.button.*;
import com.example.demowx.utils.HttpClientUtils;
import com.infoepcoh.cloud.common.util.RestTemplateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    private static final String CREATE_BUTTON_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    @Resource
    private AccessTokenService accessTokenService;

    public String addClick() {
        ButtonVo buttonVo = new ButtonVo();
        List<BaseButton> button = buttonVo.getButton();
        button.add(new ClickButton("按钮1","key01"));
        button.add(new ViewButton("按钮2","http://www.baidu.com"));
        // 子菜单
        SubButton subButton = new SubButton("菜单3");
        List<BaseButton> sub_button = subButton.getSub_button();
        sub_button.add(new ClickButton("菜单3-1", "key02"));
        sub_button.add(new ViewButton("菜单3-2","http://www.baidu.com"));
        button.add(subButton);

        String token = accessTokenService.getToken();
        // 创建请求
        try {
            return HttpClientUtils.postParameters(String.format(CREATE_BUTTON_URL, token),
                    JSON.toJSONString(buttonVo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail!";
    }
}
