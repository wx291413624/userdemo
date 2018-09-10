package com.example.userdemo.config.vo;

public enum ErrorEnum {
    ADDMINERROR("1001", "adminTokenIsNull"), USERERROR("1001", "userTokenIsNull"), USERISNULL("1002", "TokenErrorLoseEfficacy");

    private String code;
    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
