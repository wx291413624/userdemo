package com.example.userdemo.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.userdemo.wechat.config.HttpClientUtil;
import com.example.userdemo.wechat.config.WechatProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ！！ on 2017/2/17.
 * this new_czb
 */
@Component
public class AuthorizationCodeUtil {

    public static final Logger logger = LoggerFactory.getLogger(AuthorizationCodeUtil.class);


    private static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static String user_info_url = "https://api.weixin.qq.com/sns/userinfo";

    @Autowired
    private WechatProperties wxConfig;

    /**
     * @param code 微信授权返回的code
     * @return
     */
    public JSONObject getUserInfo(String code) {
        logger.debug("code = [" + code + "]");
        System.out.println(wxConfig.toString());
        //通过code获取token及openid
        String get_access_token_url = access_token_url + "?" +
                "appid=" + wxConfig.getAppid() +
                "&secret=" + wxConfig.getAppsecreet() +
                "&code=" + code +
                "&grant_type=authorization_code";
        //发送请求
        String json = HttpClientUtil.HttpSendUrl(get_access_token_url);
        System.out.println("json = " + json);
        logger.debug("getAccessToken = " + json);

        //解析为json对象
        JSONObject jsonObject = JSON.parseObject(json);
        //获取openid
        String openId = jsonObject.getString("openid");
        //获取access_token
        String access_token = jsonObject.getString("access_token");
        //拼装获取用户详细信息链接
        String get_user_info_url = user_info_url + "?access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
        //发送请求
        String userInfoStr = HttpClientUtil.HttpSendUrl(get_user_info_url);
        System.out.println("userInfoStr = " + userInfoStr);
        //转换为json
        JSONObject userInfo = JSON.parseObject(userInfoStr);

        return userInfo;
    }
}
