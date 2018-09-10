package com.example.userdemo.wechat.config;

/**
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sword.wechat4j.token.timer.AccessTokenTimer;
import org.sword.wechat4j.token.timer.JsApiTicketTimer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;

@WebListener
public class TokenListener implements ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(TokenListener.class);
    private Timer timer = null;

    public TokenListener() {
    }

    public void contextInitialized(ServletContextEvent arg0) {
        logger.info("accessToken监听器启动..........");
        this.timer = new Timer(true);
        this.registeAccessTokenTimer();
        this.registeJsApiTicketTimer();
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        this.timer.cancel();
    }

    private void registeAccessTokenTimer() {
        AccessTokenTimer accessTokenTimer = new AccessTokenTimer();
        this.timer.schedule(accessTokenTimer, 0L, 7000000L);
        logger.info("accessToken定时器注册成功，执行间隔为7000000");
    }

    private void registeJsApiTicketTimer() {
        JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer();
        this.timer.schedule(jsApiTicketTimer, 0L, 7000000L);
    }
}