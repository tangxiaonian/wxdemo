package com.example.demowx.controller;

import com.example.demowx.service.WxService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * @Classname WxController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/7/15 21:34
 * @Created by ASUS
 */
@RestController
@RequestMapping("wx")
public class WxController {

    private static final String token = "abc";

    @Resource
    private WxService wxService;

    @GetMapping(value = {"/",""})
    public void indexGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");

        System.out.println("请求进来了....");

        String[] strings = new String[]{token,timestamp,nonce};

        Arrays.sort(strings);

        String joinStr = String.join("", strings);

        if (wxService.checkSignature(joinStr, signature)) {
            System.out.println( "效验成功...." );
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
            writer.flush();
            writer.close();
        }
    }

    @PostMapping(value = {"/",""})
    public void indexPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        System.out.println( "Post 接收到了...." );

        Map<String, String> requestMap = wxService.requestMap(request.getInputStream());

        System.out.println(requestMap);

        wxService.responseMessage(response,requestMap);
    }
}