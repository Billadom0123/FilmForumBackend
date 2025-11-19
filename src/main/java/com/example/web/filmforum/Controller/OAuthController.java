package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Service.Login.UserDetailsServiceImpl;
import com.example.web.filmforum.Service.Login.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
    @Value("${oauth2.qq.failureRedirect}")
    private String failureRedirect;
    @Value("${oauth2.qq.successRedirect}")
    private String successRedirect;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;



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
    public void qqCallback(@RequestParam("code") String code, @RequestParam("state") String state,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        try {
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

            // 根据获取到的用户信息进行登录或注册逻辑
            UserPO user = userService.findOrCreateByOpenId(openId, userInfoJson);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            // 强制创建 session 并写入安全上下文（使用常量 key）
            request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            response.sendRedirect(successRedirect);
        } catch (Exception e) {
            response.sendRedirect(failureRedirect + "?msg=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        }
        /*
          userInfoJson 结构示例：
          {
           "ret": 0,
           "msg": "",  // 返回信息, 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
           "nickname": "YourNickname",
           "figureurl": "http://qzapp.qlogo.cn/qzapp/1100000000/1100000000-1234567890-1000/30",
           "figureurl_1": "http://qzapp.qlogo.cn/qzapp/1100000000/1100000000-1234567890-1000/50",
           "figureurl_2": "http://qzapp.qlogo.cn/qzapp/1100000000/1100000000-1234567890-1000/100",
           "figureurl_qq_1": "http://qzapp.qlogo.cn/qzapp/1100000000/1100000000-1234567890-1000/40",
           "figureurl_qq_2": "http://qzapp.qlogo.cn/qzapp/1100000000/1100000000-1234567890-1000/100",
           "gender": "男",
           ...
          }
         */
    }
}
