package com.example.web.filmforum.Payload;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Util.H;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private Long total;
    private int page;
    private int size;
    private boolean hasNext;


    public JSONObject toJSON() {
        return H.build()
                .put("total", total)
                .put("page", page)
                .put("size", size)
                .put("has_next", hasNext)
                .toJson();
    }
}
