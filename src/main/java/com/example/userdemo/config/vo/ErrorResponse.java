package com.example.userdemo.config.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String msg;

    public String toJson() {
        return JSONObject.toJSONString(this);
    }


}

