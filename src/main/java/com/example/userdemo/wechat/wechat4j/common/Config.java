package com.example.userdemo.wechat.wechat4j.common;

public class Config {
    public static String url;
    public static String token;
    public static String encodingAESKey;
    public static String appid;
    public static String appSecret;
    public static String accessTokenServer;
    public static String jsApiTicketServer;
    public static  Config config = new Config();



    public static Config instance() {
        return config;
    }

    public String getToken() {
        return token;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getUrl() {
        return url;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public String getAccessTokenServer() {
        return accessTokenServer;
    }

    public String getJsApiTicketServer() {
        return jsApiTicketServer;
    }


    public static void setUrl(String url) {
        Config.url = url;
    }

    public static void setToken(String token) {
        Config.token = token;
    }

    public static void setEncodingAESKey(String encodingAESKey) {
        Config.encodingAESKey = encodingAESKey;
    }

    public static void setAppid(String appid) {
        Config.appid = appid;
    }

    public static void setAppSecret(String appSecret) {
        Config.appSecret = appSecret;
    }

    public static void setAccessTokenServer(String accessTokenServer) {
        Config.accessTokenServer = accessTokenServer;
    }

    public static void setJsApiTicketServer(String jsApiTicketServer) {
        Config.jsApiTicketServer = jsApiTicketServer;
    }

}
