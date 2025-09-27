package com.example.web.filmforum.Util;

import com.alibaba.fastjson2.JSONObject;

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


}
