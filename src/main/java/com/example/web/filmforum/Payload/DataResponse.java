package com.example.web.filmforum.Payload;


import com.example.web.filmforum.Payload.Enums.CommonErr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataResponse {
    private int code;
    private String message;
    private Object data;

    public static DataResponse ok() { return new DataResponse(200, "ok", null); }

    public static DataResponse success(Object data) { return new DataResponse(200, "success", data); }

    public static DataResponse failure(int code, String message) { return new DataResponse(code, message, null); }

    public static DataResponse failure(CommonErr commonErr) { return new DataResponse(commonErr.getCode(), commonErr.getMessage(), null); }

}
