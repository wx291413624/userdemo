package com.example.userdemo.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 11 on 2016/11/30.
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {


    private String token;
    private String appid;
    private String appsecreet;
    private String url;
    private String encodingaeskey;
    private String accessToken;
    private String jsapi;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppid() { return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecreet() { return appsecreet;
    }

    public void setAppsecreet(String appsecreet) {
        this.appsecreet = appsecreet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncodingaeskey() {
        return encodingaeskey;
    }

    public void setEncodingaeskey(String encodingaeskey) {
        this.encodingaeskey = encodingaeskey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJsapi() {
        return jsapi;
    }

    public void setJsapi(String jsapi) {
        this.jsapi = jsapi;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "token='" + token + '\'' +
                ", appid='" + appid + '\'' +
                ", appsecreet='" + appsecreet + '\'' +
                ", url='" + url + '\'' +
                ", encodingaeskey='" + encodingaeskey + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", jsapi='" + jsapi + '\'' +
                '}';
    }
}
