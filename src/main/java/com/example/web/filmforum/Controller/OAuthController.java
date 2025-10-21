package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/oauth2")
public class OAuthController {

    @Value("${oauth2.qq.appId}")
    private String appId;
    @Value("${oauth2.qq.appKey}")
    private String appSecret;
    @Value("${oauth2.qq.redirectUri}")
    private String redirectUri;



    @GetMapping("/qq")
    public void qqLogin(HttpServletResponse response) throws IOException {
        // 跳转到QQ授权页
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code"
                + "&client_id=" + appId
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&state=excellent"
                + "&scope=get_user_info";
        response.sendRedirect(url);
    }

    @GetMapping("/qq/callback")
    public String qqCallback(@RequestParam("code") String code, @RequestParam("state") String state) {

        String tokenUrl = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=" + appId
                + "&client_secret=" + appSecret // 替换为你的App Secret
                + "&code=" + code
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&fmt=" + "json"; // 返回JSON格式
        // 使用RestTemplate或WebClient发送请求并处理响应
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(tokenUrl, String.class);
        // 处理返回的结果，提取access_token等信息
        JSONObject tokenJson = JSONObject.parseObject(result);
        String accessToken = tokenJson.getString("access_token");

        String openIdUrl = "https://graph.qq.com/oauth2.0/me?access_token=" + accessToken + "&fmt=json";
        String openIdResult = restTemplate.getForObject(openIdUrl, String.class);
        JSONObject openIdJson = JSONObject.parseObject(openIdResult);
        String openId = openIdJson.getString("openid");

        // 这里可以根据access_token和openId获取用户信息
        String userInfoUrl = "https://graph.qq.com/user/get_user_info?access_token=" + accessToken
                + "&oauth_consumer_key=" + appId
                + "&openid=" + openId;
        String userInfoResult = restTemplate.getForObject(userInfoUrl, String.class);
        JSONObject userInfoJson = JSONObject.parseObject(userInfoResult);

        System.out.println(userInfoJson);





        return "Code: " + code + ", State: " + state;
    }
}
