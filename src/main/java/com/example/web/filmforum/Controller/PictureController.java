package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/picture")
public class PictureController {

    @PostMapping("/upload")
    public DataResponse uploadPicture(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return DataResponse.failure(CommonErr.FILE_NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("strategy", 2);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        String url = "https://pic.billadom.top/api/v1/upload";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return DataResponse.failure(CommonErr.NETWORK_WRONG);
        }
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        if (responseBody == null) {
            return DataResponse.failure(CommonErr.FILE_UPLOAD_ERR);
        }
        if (responseBody.getBoolean("status")) {
            JSONObject urls = responseBody.getJSONObject("data").getJSONObject("links");
            return DataResponse.success(urls);
        } else {
            return DataResponse.failure(CommonErr.FILE_UPLOAD_ERR);
        }
    }
}
