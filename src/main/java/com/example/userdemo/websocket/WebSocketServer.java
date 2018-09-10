//package com.example.userdemo.websocket;
//
//import com.example.userdemo.config.ApplicationContextRegister;
//import com.example.userdemo.domain.SysUser;
//import com.example.userdemo.redis.RedisService;
//import com.example.userdemo.util.ResponseUtill;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @Author: 夜凉如水般清澈
// * @Date: 18-4-24 下午5:17
// * Fear can hold you prisoner, hope can set you free. A strong man can save himself
// */
//@Slf4j
//@Data
//@ServerEndpoint("/websocket/{token}")
//@Component
//public class WebSocketServer {
//
//    private static AtomicInteger onlineCount = new AtomicInteger(0);
//
//    /**
//     * private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
//     */
//    public static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>();
//    private Session session;
//    private SysUser sysUser;
//    private String token;
//
//    private RedisService redisService = ApplicationContextRegister.getApplicationContext().getBean(RedisService.class);
//
//    @OnOpen
//    public void onOpen(@PathParam("token") String token, Session session) {
//        sysUser = redisService.findUser(token);
//        this.token = token;
//        this.session = session;
//        webSocketSet.put(token, this);
//        addOnlineCount();
//        //登录通知
//        allOnMessage(ResponseUtill.responseAccessDate(sysUser).toJson());
//    }
//
//    @OnClose
//    public void onClose() {
//        SysUser user = sysUser;
//        WebSocketServer webSocketServer = webSocketSet.get(this.getToken());
//        if (webSocketServer != null) {
//            webSocketSet.remove(this.getToken());
//            subOnlineCount();
//            //下线通知
//            allOnMessage(ResponseUtill.responseMsgDate(202, user).toJson());
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        UserAndMsgVo userAndMsgVo = new UserAndMsgVo(this.sysUser);
//        userAndMsgVo.setMsg(message);
//        //收到消息
//        allOnMessage(ResponseUtill.responseMsgDate(203, userAndMsgVo).toJson());
//    }
//
//    public void allOnMessage(String message) {
//        for (WebSocketServer item : webSocketSet.values()) {
//            try {
//                //消息 发送所有人
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
//        error.printStackTrace();
//    }
//
//
//    public void sendMessage(String message) throws IOException {
//        this.session.getAsyncRemote().sendText(message);
//    }
//
//
//    public static AtomicInteger getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static void addOnlineCount() {
//        WebSocketServer.onlineCount.getAndIncrement();
//    }
//
//    public static void subOnlineCount() {
//        WebSocketServer.onlineCount.getAndDecrement();
//    }
//}
