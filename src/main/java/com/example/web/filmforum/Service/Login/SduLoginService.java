package com.example.web.filmforum.Service.Login;

import com.example.web.filmforum.Util.Des;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SduLoginService {

    String CAS_LOGIN_PAGE = "https://cas.sdu.edu.cn/cas/login";

    String GATE_WAY = "https://servicedesk.sdu.edu.cn/idc/index.jsp";

    String lt = null;

    private static final HashMap<String, String> casLoginCookie = new HashMap<>();

    // 统一认证登入 设备验证components（该值可替换）
    private static final String components = "Film-Forum-Web-Sdu-Login-Service";
    // 统一认证登入 设备验证details
    private static final String details = "Film-Forum-Web-Sdu-Login-Service";

    private static final String murmur = Hashing.murmur3_128(31).hashString(components, StandardCharsets.UTF_8).toString();
    private static final String murmur_s = Hashing.murmur3_128(31).hashString(details, StandardCharsets.UTF_8).toString();
    private static final String murmur_md5 = DigestUtils.md5Hex(details);


    private void enterCasLoginPage(String casLoginURL) throws Exception {
        // 创建 CookieStore 和 HttpClientContext
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 发送 GET 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(casLoginURL);
            try (CloseableHttpResponse response = client.execute(get, context)) {
                String html = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                // 提取 Cookie
                String JSESSIONID = null, cookie_adx = null;
                for (Cookie cookie : cookieStore.getCookies()) {
                    if ("JSESSIONID".equals(cookie.getName())) {
                        JSESSIONID = cookie.getValue();
                    }
                    if ("cookie-adx".equals(cookie.getName())) {
                        cookie_adx = cookie.getValue();
                    }
                }
                casLoginCookie.put("cookie-adx", cookie_adx);
                casLoginCookie.put("JSESSIONID", JSESSIONID);
                casLoginCookie.put("Language", "zh-CN");

                // 提取 lt 字段
                Document doc = Jsoup.parse(html);
                lt = doc.select("input[name=lt]").attr("value");
            }
        }
    }

    private void deviceCheck(String sdu_id, String sdu_password) throws Exception {
        // 构建表单参数
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("d", murmur));
        params.add(new BasicNameValuePair("d_s", murmur_s));
        params.add(new BasicNameValuePair("d_md5", murmur_md5));
        params.add(new BasicNameValuePair("m", "1"));
        params.add(new BasicNameValuePair("u", Des.strEnc(sdu_id, "1", "2", "3")));
        params.add(new BasicNameValuePair("p", Des.strEnc(sdu_password, "1", "2", "3")));

        // 设置 Cookie
        BasicCookieStore cookieStore = new BasicCookieStore();
        casLoginCookie.forEach((k, v) -> {
            org.apache.http.impl.cookie.BasicClientCookie cookie = new org.apache.http.impl.cookie.BasicClientCookie(k, v);
            cookie.setDomain("pass.sdu.edu.cn");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
        });
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 发送 POST 请求
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost("https://pass.sdu.edu.cn/cas/device");
            post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            try (CloseableHttpResponse response = client.execute(post, context)) {
                String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                // 解析 JSON 响应
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> deviceInfo = mapper.readValue(json, Map.class);
                System.out.println(deviceInfo.get("info"));

                switch (deviceInfo.get("info")) {
                    case "binded":
                    case "pass":
                    case "bind":
                        System.out.println("设备验证通过，准备登入");
                        break;
                    case "validErr":
                    case "notFound":
                        System.out.println("密码错误或用户不存在");
                        throw new RuntimeException();
                    case "mobileErr":
                        System.out.println("未绑定手机");
                        throw new RuntimeException();
                }
            }
        }
    }
}
