package com.example.userdemo.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.userdemo.wechat.util.AuthorizationCodeUtil;
import com.example.userdemo.wechat.util.HttpXmlClient;
import com.example.userdemo.wechat.wechat4j.WechatSupport;
import com.example.userdemo.wechat.wechat4j.common.Config;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.WechatResponse;
import org.sword.wechat4j.token.TokenProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ye on 2017/2/7.
 * this new_czb
 */
@RestController
@RequestMapping("/wechat")
@Scope("prototype")
public class WechatController extends WechatSupport {


    @Autowired
    private AuthorizationCodeUtil authorizationCodeUtil;

    /**
     * 微信时间消息触发器
     *
     * @param request web request
     * @return 给微信返回 xml格式的参数
     * @throws IOException
     */
    @RequestMapping("weixin")
    @ResponseBody
    public String weixin(HttpServletRequest request) throws IOException {
        this.InitRequest(request);
        return this.execute();
    }

    @Override
    public void InitRequest(HttpServletRequest request) {

        this.request = request;
        this.wechatRequest = new WechatRequest();
        this.wechatResponse = new WechatResponse();
    }


    /**
     * 微信换授权域时使用
     */
    @RequestMapping("weixinauth")
    @ResponseBody
    public String weixin(HttpServletRequest request,
                         HttpServletResponse response, String echostr) throws IOException {

        return echostr;
    }


    /**
     * 获取js签名
     *
     * @param url js签名必要参数 ""
     * @return
     */
    //获取js签名
    @RequestMapping("getSignature")
    @ResponseBody
    public String getSignature(@RequestParam(required = true) String url) {

        //获取#前url
        url = url.split("#")[0];
        logger.info("url = " + url);
        //获取全局配置文件
        Config config = Config.instance();

        String jsapi_ticket = TokenProxy.jsApiTicket();
        logger.info("jsapi_ticket=" + TokenProxy.jsApiTicket());

        //获取签名signature
        String noncestr = UUID.randomUUID().toString();
        logger.info("noncestr = " + noncestr);

        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        logger.info("timestamp = " + timestamp);
        String str =
                "jsapi_ticket=" + jsapi_ticket +
                        "&noncestr=" + noncestr +
                        "&timestamp=" + timestamp +
                        "&url=" + url;

        //sha1加密
        String signature = HttpXmlClient.SHA1(str);
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", config.getAppid());
        map.put("noncestr", noncestr);
        map.put("timestamp", timestamp);
        map.put("signature", signature);

        return JSON.toJSONString(map);
    }

    /**
     * @param request
     * @param code    授权code
     *                23472345166`  @param os       此处写wechat
     * @return 跳转页面
     */
    @RequestMapping("authorization")
    public Object authorization(HttpServletRequest request,
                                @Param("code") String code) {
        logger.info("code = " + code);
        JSONObject userInfo = authorizationCodeUtil.getUserInfo(code);

        String openid = userInfo.getString("openid");
        String unionid = userInfo.getString("unionid");

        if (StringUtils.isEmpty(openid)) {
            return "授权失败......";
        }

        //記錄信息

        logger.debug("unionid================" + unionid);

        if (StringUtils.isEmpty(openid)) {
            return "openid is null";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("opendId", openid);
        jsonObject.put("unionid", unionid);
        jsonObject.put("imgUrl", userInfo.getString("headimgurl"));
        return jsonObject;
    }


    /**
     * 文本消息
     */
    @Override
    protected void onText() {
        try {
            String s = TokenProxy.accessToken();
            System.out.println("s = " + s);
            String content = wechatRequest.getContent();
            logger.info("content======" + content);

            String redirect_uri = "http://shop.xiongmaoxingchu.com/wechat/repair/repair/index.html";

            String url13 = "http://shop.xiongmaoxingchu.com/wechat/repair/repair/html/feedback.html";

            String url14 = "http://shop.xiongmaoxingchu.com/wechat/repair/repair/html/otherProblems.html";

            if ("1".equals(content)) {
                this.responseText("<a href='" + GGurl(redirect_uri) + "'>投诉详情</a>");
            } else if ("2".equals(content)) {
                this.responseText("<a href='" + GGurl(url13) + "'>意见反馈</a>");
            } else if ("3".equals(content)) {
                this.responseText("<a href='" + GGurl(url14) + "'>其它问题</a>");
            } else if ("VIP商户服务包".equals(content.toUpperCase())) {
                String md = "j7XisaCJIOt3R00eSD7a32-tk-lMwYk0vpakhWtWaXc";
                responseImage(md);
            } else {
                this.responseKeFuText(content);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

//        this.responseKeFuText(content);

//        IncomingUtil.onText(content);
    }

    /**
     * 图片消息
     */
    @Override
    protected void onImage() {
        logger.debug("onImageEvent");
//        String picUrl = wechatRequest.getPicUrl();
//        String MediaId = wechatRequest.getMediaId();
//        String MsgId = wechatRequest.getMsgId();
//
//        String result = "图片消息picUrl:" + picUrl + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//        logger.info("result : \r\n"+result);
//        responseText(result);
        // responseImage(mediaId);
    }

    /**
     * 语音消息
     */
    @Override
    protected void onVoice() {
        logger.debug("onVoiceEvent");
//        String Format = wechatRequest.getFormat();
//        String MediaId = wechatRequest.getMediaId();// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
//        String MsgId = wechatRequest.getMsgId();
//
//        String result = "语音消息Format:" + Format + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//        logger.info(result);
//        responseText(result);
        // responseVoice(mediaId);

        // 回复音乐消息
        // MusicResponse music = new MusicResponse();
        // music.setTitle(title);
        // music.setDescription(description);
        // music.setMusicURL(musicURL);
        // music.setHQMusicUrl(hQMusicUrl);
        // music.setThumbMediaId(thumbMediaId);
        // responseMusic(music);
        //
        // responseMusic(title, description, musicURL, hQMusicUrl,
        // thumbMediaId);
    }

    /**
     * 视频消息
     */
    @Override
    protected void onVideo() {
        logger.debug("onVideoEvent");
//        String ThumbMediaId = wechatRequest.getThumbMediaId();
//        String MediaId = wechatRequest.getMediaId();// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
//        String MsgId = wechatRequest.getMsgId();
//
//        String result = "视频消息ThumbMediaId:" + ThumbMediaId + ", MediaId:" + MediaId + ", MsgId:" + MsgId;
//        logger.info(result);
//        responseText(result);

        // 回复视频消息
        // VideoResponse video = new VideoResponse();
        // video.setTitle(title);
        // video.setDescription(description);
        // video.setMediaId(mediaId);
        // responseVideo(video);
        //
        // responseVideo(mediaId, title, description);
    }

    /**
     * 地理位置消息
     */
    @Override
    protected void onLocation() {
        logger.debug("onLocationEvent");
        String Location_X = wechatRequest.getLocation_X();
        String Location_Y = wechatRequest.getLocation_Y();
        String Scale = wechatRequest.getScale();
        String Label = wechatRequest.getLabel();
        String MsgId = wechatRequest.getMsgId();

        String result = "地理位置消息Location_X:" + Location_X + ", Location_Y:" + Location_Y + ", Scale:" + Scale
                + ", Label:" + Label + ", MsgId:" + MsgId;
        logger.info(result);
        responseText(result);
    }

    /**
     * 链接消息
     */
    @Override
    protected void onLink() {
        logger.debug("onLinkEvent");
//        String Title = wechatRequest.getTitle();
//        String Description = wechatRequest.getDescription();
//        String Url = wechatRequest.getUrl();
//        String MsgId = wechatRequest.getMsgId();
//
//        String result = "链接消息Title:" + Title + ", Description:" + Description + ", Url:" + Url + ", MsgId:" + MsgId;
//        logger.info(result);
//        responseText(result);
    }

    /**
     * 未知消息类型，错误处理
     */
    @Override
    protected void onUnknown() {
        logger.debug("onUnknownEvent");
        // String msgType = wechatRequest.getMsgType();
        //
        // String result = "未知消息msgType:" + msgType;
        // logger.info(result);
        // responseText(result);

    }

    /**
     * 扫描二维码Event,绑定上下级关系
     */
    @Override
    protected void scan() {
        logger.info("scan");
        String openId = wechatRequest.getFromUserName();
        String eventKey = wechatRequest.getEventKey();
        if (eventKey != null) {
//            int refUserId = Integer.parseInt(eventKey);
//            UserTemp userTempByOpenId = userTempService.getUserTempByOpenId(openId);
//            if (userTempByOpenId == null) {
//                UserTemp userTemp = new UserTemp();
//                userTemp.setUserId(refUserId);
//                userTemp.setOpenId(openId);
//                userTemp.setCreateDt(new Date());
//                userTemp.setOpenType(1);// 1代表微信
//                userTempService.createUserTemp(userTemp);// 将推荐人id和自己的openid先保存
            //  }
        }
    }

    /**
     * 关注公众号或扫码关注触发订阅Event
     */
    @Override
    protected void subscribe() {
        logger.info("scanEvent================================");
        //qrscene_300038
//        // 获取openId
        String openId = wechatRequest.getFromUserName();


        //通过open id来获得userTemp
        //    UserTemp userTemp = userTempService.getUserTempByOpenId(openId);
        //查询user Temp没有
        if (1 == 2) {

//            UserTemp userTemp1 = new UserTemp();
//            String eventKey = wechatRequest.getEventKey();// 获取扫码的参数
//            logger.info("eventKey = " + eventKey);
//            if (!StringUtils.isEmpty(eventKey)) {
//                String[] s = eventKey.split("_");
//                logger.info(s[1]);
//                Integer userId = Integer.parseInt(s[1]);
//                userTemp1.setUserId(userId);
//            }
//            userTemp1.setOpenId(openId);
//            userTemp1.setCreateDt(new Date());
//
//            userTempService.createUserTemp(userTemp1);

        }
        String urls = "";
        try {
            String authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
            String redirect_uri = "http://shop.xiongmaoxingchu.com";
            String encode = URLEncoder.encode(redirect_uri, "utf-8");
            urls = authorize_url + "?" +
                    "appid=" + Config.instance().getAppid() +//*应用唯一标识
                    "&redirect_uri=" + encode +//*重定向地址，需要进行UrlEncode
                    "&response_type=code" +//*填code
                    "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                    "&state=dashboard" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                    "&connect_redirect=1#wechat_redirect";//*作用未知
        } catch (Exception e) {
            e.printStackTrace();
        }
        String a = "<a href=" + urls + ">熊猫星厨商户中心</a>";
//        this.responseNew("", "Join Us", "http://shop.xiongmaoxingchu.com/wechat/1232.jpg", urls);

        String ss = "您好，欢迎加入熊猫星厨这个大家庭，感谢您对熊猫星厨的信任，往后在合作期间，您这边有任何问题需要反馈及投诉，都可以随时在此页面联系我";
        this.responseText(ss);


    }

    /**
     * 取消订阅Event
     */
    @Override
    protected void unSubscribe() {
        logger.debug("unSubscribeEvent");
        String FromUserName = wechatRequest.getFromUserName();
        String result = "取消订阅EventFromUserName:" + FromUserName;
        logger.info(result);
        responseText(result);
    }

    /**
     * 点击菜单跳转链接时的Event推送
     */
    @Override
    protected void view() {
        // 获取openId
        String openId = wechatRequest.getFromUserName();
        //获取关注者的信息并保存
//        String accessToken = TokenProxy.accessToken();
//        String get_user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
//        String json = HttpClientUtil.HttpSendUrl(get_user_info_url);
//        JSONObject jsonObject = JSON.parseObject(json);
//        logger.debug("subscribe====json" + json);


    }

    /**
     * 自定义菜单Event
     */
    @Override
    protected void click() {

        String eventKey = wechatRequest.getEventKey();
        System.out.println("eventKey = " + eventKey);
        if ("tousu".equals(eventKey)) {
            this.responseText("欢迎进入熊猫星厨商户投诉中心：\n" +
                    "请您按序号选择需要反馈的问题：\n" +
                    "1. 商户投诉\n" +
                    "2. 意见和建议反馈\n" +
                    "3. 其他问题反馈");
        }

    }

    /**
     * 上报地理位置Event
     */
    @Override
    protected void location() {
        logger.debug("locationEvent");
        String Latitude = wechatRequest.getLatitude();
        String Longitude = wechatRequest.getLongitude();
        String Precision = wechatRequest.getPrecision();
        String result = "上报地理位置EventLatitude:" + Latitude + ", Longitude:" +
                Longitude + ", Precision:" + Precision;
        logger.info(result);
        responseText(result);
    }

    /**
     * 模板消息发送成功推送Event
     */
    @Override
    protected void templateMsgCallback() {
        logger.debug("templateMsgCallbackEvent");
        // String MsgID = wechatRequest.getMsgId();
        // String Status = wechatRequest.getStatus();
        // String result = "模板消息发送成功推送EventMsgID:" + MsgID + ", Status:" + Status;
        // logger.info(result);
    }

    /**
     * 弹出地理位置选择器的Event
     */
    @Override
    protected void locationSelect() {
        logger.debug("locationSelectEvent");
        // String Location_X =
        // wechatRequest.getSendLocationInfo().getLocation_X();
        // String Location_Y =
        // wechatRequest.getSendLocationInfo().getLocation_Y();
        // String Scale = wechatRequest.getSendLocationInfo().getScale();
        // String Label = wechatRequest.getSendLocationInfo().getLabel();
        // String Poiname = wechatRequest.getSendLocationInfo().getPoiname();
        // String result = "弹出地理位置选择器的EventLocation_X:" + Location_X +
        // ", Location_Y:" + Location_Y+
        // ", Scale:" + Scale+
        // ", Label:" + Label+
        // ", Poiname:" + Poiname;
        // logger.info(result);
        // responseText(result);
    }

    /**
     * 弹出拍照或者相册发图的Event
     */
    @Override
    protected void picPhotoOrAlbum() {
        logger.debug("picPhotoOrAlbumEvent");
        // String Count = wechatRequest.getSendPicsInfo().getCount();
        // String PicMd5Sum = "";
        // if(StringUtils.isNotBlank(Count) && !Count.equals("0")){
        // PicMd5Sum =
        // wechatRequest.getSendPicsInfo().getItem().get(0).getPicMd5Sum();
        // }
        // String result = "弹出系统拍照发图的EventCount:" + Count + ", PicMd5Sum:" +
        // PicMd5Sum;
        // logger.info(result);
        // responseText(result);
    }

    /**
     * 弹出系统拍照发图的Event
     */
    @Override
    protected void picSysPhoto() {
        logger.debug("picSysPhotoEvent");
        // String Count = wechatRequest.getSendPicsInfo().getCount();
        // String result = "弹出系统拍照发图的EventCount:" + Count ;
        // logger.info(result);
        // responseText(result);
    }

    /**
     * 弹出微信相册发图器的Event推送
     */
    @Override
    protected void picWeixin() {
        logger.debug("picWeixinEvent");
        // String Count = wechatRequest.getSendPicsInfo().getCount();
        // String result = "弹出系统拍照发图的EventCount:" + Count ;
        // logger.info(result);
        // responseText(result);
    }

    /**
     * 扫码推Event
     */
    @Override
    protected void scanCodePush() {
        logger.debug("scanCodePushEvent");
        // String ScanType = wechatRequest.getScanCodeInfo().getScanType();
        // String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
        // String result = "扫码推EventScanType:" + ScanType + ", ScanResult:" +
        // ScanResult;
        // logger.info(result);
        // responseText(result);
    }

    /**
     * 扫码推Event且弹出“消息接收中”提示框的Event
     */
    @Override
    protected void scanCodeWaitMsg() {
        logger.debug("scanCodeWaitMsgEvent");
        // String ScanType = wechatRequest.getScanCodeInfo().getScanType();
        // String ScanResult = wechatRequest.getScanCodeInfo().getScanResult();
        // String result = "扫码推EventScanType:" + ScanType + ", ScanResult:" +
        // ScanResult;
        // logger.info(result);
        // responseText(result);
    }

    @Override
    protected void kfCreateSession() {
        logger.debug("kfCreateSessionEvent");

    }

    @Override
    protected void kfCloseSession() {
        logger.debug("kfCloseSessionEvent");

    }

    @Override
    protected void kfSwitchSession() {
        logger.debug("kfSwitchSessionEvent");

    }

    @Override
    protected void onShortVideo() {
        logger.debug("onShortVideoEvent");

    }


    public String GGurl(String url) {

        String authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String encode = null;
        try {
            encode = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url1 = authorize_url + "?" +
                "appid=" + org.sword.wechat4j.common.Config.instance().getAppid() +//*应用唯一标识
                "&redirect_uri=" + encode +//*重定向地址，需要进行UrlEncode
                "&response_type=code" +//*填code
                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                "&state=charts" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                "&connect_redirect=1#wechat_redirect";//*作用未知

        return url1;
    }

}
