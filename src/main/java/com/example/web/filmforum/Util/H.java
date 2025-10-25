package com.example.web.filmforum.Util;

import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class H {

    private JSONObject jsonObject;

    public static H build() {
        return new H();
    }

    public H put(String key, Object value) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        jsonObject.put(key, value);
        return this;
    }

    public JSONObject toJson() {
        return jsonObject;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }


}
