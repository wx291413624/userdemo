package com.example.userdemo.util;

public enum BaseEnum {
    ERROR(500, "error"), ACCESS(200, "access");

    private Integer code;
    private String msg;

    BaseEnum(Integer i, String error) {
        this.code = i;
        this.msg = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
