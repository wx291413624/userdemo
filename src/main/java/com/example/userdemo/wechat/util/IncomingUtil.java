package com.example.userdemo.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this new_czb
 */
public class IncomingUtil {


    private static String url = "https://hooks.pubu.im/services/v3pby7lvk6rruw7";


    /**
     * 发送一个一JSONObject 为参数的 post请求
     *
     * @param url  发送请求的地址
     * @param json 发送请求的 json实体
     * @return 相应
     * @throws IOException
     */
    private static String jsonSend(String url, JSONObject json) throws IOException {

        //新建post请求
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient client = HttpClients.createDefault();

        String respContent = null;

        //设置请求头
        httpPost.setHeader("Content-Type", "application/json");
        //实例化json参数
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        //填充参数
        httpPost.setEntity(entity);
        //发送请求
        HttpResponse resp = client.execute(httpPost);

        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }

    /**
     * 发送纯文本消息
     *
     * @param msg 消息内容
     * @return Boolean
     */
    public static Boolean onText(String msg) {
        JSONObject body = new JSONObject();
        body.put("text", msg);
        try {
            jsonSend(url, body);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Boolean onCode(String s) {

        JSONObject jsonObject = new JSONObject();


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("code", s);
        jsonObject1.put("type", "java");
        jsonObject1.put("name", outFormat.format(new Date()));
        jsonObject.put("snippet", jsonObject1);

        try {
            jsonSend(url, jsonObject);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
/**
 *{
 "text": "文本",
 "channel": "#频道名字 或 @用户名字",
 "photoUrl": "图片 URL",
 "attachments": [{
 "title": "标题",
 "description": "描述",
 "url": "链接",
 "color": "warning|info|primary|error|muted|success"
 }],
 "displayUser": {
 "name": "应用名称",
 "avatarUrl": "头像地址"
 },
 "buttons": [
 {
 "text": "button label",
 "url": "http://domain.com/foo.html",
 "action": "action_1",
 "callbackUrl": "http://foo.dev/inline-button-handler"
 }
 ]
 }
 */


}
