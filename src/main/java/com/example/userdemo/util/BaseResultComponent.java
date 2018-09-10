package com.example.userdemo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class BaseResultComponent {

    private Integer code;
    private String msg;
    private Object t;

    public BaseResultComponent(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResultComponent(Integer code, Object t) {
        this.code = code;
        this.t = t;
    }

    public BaseResultComponent(Object t) {
        this.msg = BaseEnum.ACCESS.getMsg();
        this.code = BaseEnum.ACCESS.getCode();
        this.t = t;
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }
}
