package com.example.userdemo.util;


public class ResponseUtill {

    /**
     * 成功 返回数据
     *
     * @param t
     * @return
     */
    public static BaseResultComponent responseAccessDate(Object t) {
        return new BaseResultComponent(t);
    }

    /**
     * 成功返回状态码
     *
     * @return
     */
    public static BaseResultComponent responseAccessDate() {
        return new BaseResultComponent(BaseEnum.ACCESS.getCode(), BaseEnum.ACCESS.getMsg());
    }

    /**
     * 失败返回状态码
     *
     * @return
     */
    public static BaseResultComponent responseErrorDate() {
        return new BaseResultComponent(BaseEnum.ERROR.getCode(), BaseEnum.ERROR.getMsg());
    }

    /**
     * 失败返回原因
     *
     * @param msg
     * @return
     */
    public static BaseResultComponent responseErrorDate(String msg) {
        return new BaseResultComponent(BaseEnum.ERROR.getCode(), msg);
    }

    /**
     * 返回自定义状态码 原因
     *
     * @param code
     * @param msg
     * @return
     */
    public static BaseResultComponent responseMsg(Integer code, String msg) {
        return new BaseResultComponent(code, msg);
    }

    /**
     * 返回自定义状态  参数
     *
     * @param code
     * @param data
     * @return
     */
    public static BaseResultComponent responseMsgDate(Integer code, Object data) {
        return new BaseResultComponent(code, data);
    }
}
