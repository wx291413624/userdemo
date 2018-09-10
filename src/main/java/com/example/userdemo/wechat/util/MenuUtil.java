package com.example.userdemo.wechat.util;

/**
 *
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.menu.Menu;
import org.sword.wechat4j.menu.MenuButton;
import org.sword.wechat4j.menu.MenuManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单创建工具类
 *
 * @author glei
 */

@Component
@ConfigurationProperties(prefix = "server")
public class MenuUtil {


    private String url;

    private String contextPath;


    private final static Logger logger = LoggerFactory.getLogger(MenuUtil.class);
    /**
     * 菜单工具类
     */

    /**
     * 创建菜单
     *
     * @throws Exception
     */
    public void createMenu() throws Exception {
        MenuManager menuManager = new MenuManager();
        Menu menu = new Menu();

        List<MenuButton> buttons = new ArrayList<MenuButton>();
        buttons.add(createbutton1());
        buttons.add(createbutton3());
        buttons.add(createbutton2());

        menu.setButton(buttons);

        //删除菜单
        logger.info("删除菜单。。。。。。");
        deleteMenu();

        //初始化菜单
        menuManager.create(menu);
        logger.info("初始化菜单完成。。。。");
    }

    /**
     * 删除菜单
     *
     * @throws Exception
     */
    public void deleteMenu() throws Exception {
        MenuManager menuManager = new MenuManager();
        menuManager.delete();
    }

    /**
     * 查看菜单
     *
     * @throws Exception
     */
    public void queryMenu() throws Exception {
        MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.getMenu();
        List<MenuButton> list = menu.getButton();
        for (MenuButton mb : list) {
            List<MenuButton> subbuttons = mb.getSubButton();
            logger.info(subbuttons.get(0).getName());
        }
    }

    /**
     * 创建第一个菜单项
     *
     * @return
     */
    public MenuButton createbutton1() throws UnsupportedEncodingException {
        String authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
        MenuButton allButton = new MenuButton();
        allButton.setName("熊猫故事");


        MenuButton menuButton = new MenuButton();
        menuButton.setName("主页");
        String redirect_uri = "http://shop.xiongmaoxingchu.com";
        menuButton.setType(EventType.view);
        menuButton.setUrl(redirect_uri);


        MenuButton menuButton1 = new MenuButton();
        menuButton1.setName("服务介绍");

        String redirect_uri1 = "";
        String encode1 = URLEncoder.encode(redirect_uri1, "utf-8");
        String url2 = authorize_url + "?" +
                "appid=" + Config.instance().getAppid() +//*应用唯一标识
                "&redirect_uri=" + encode1 +//*重定向地址，需要进行UrlEncode
                "&response_type=code" +//*填code
                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                "&state=STATE" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                "&connect_redirect=1#wechat_redirect";//*作用未知
        menuButton1.setType(EventType.view);
        menuButton1.setUrl(url2);
//-----

        MenuButton mund = new MenuButton();
        mund.setName("常见问题");

        String redirect_sss = url + "/wechat/activity/friend.html";
        String encosss = URLEncoder.encode(redirect_sss, "utf-8");
        String usss = authorize_url + "?" +
                "appid=" + Config.instance().getAppid() +//*应用唯一标识
                "&redirect_uri=" + encosss +//*重定向地址，需要进行UrlEncode
                "&response_type=code" +//*填code
                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                "&state=STATE" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                "&connect_redirect=1#wechat_redirect";//*作用未知
        mund.setType(EventType.view);
        mund.setUrl(usss);


        List<MenuButton> list = new ArrayList<>();

        list.add(menuButton);
        list.add(menuButton1);
        list.add(mund);
        allButton.setSubButton(list);


        return allButton;
    }

    /**
     * 创建第二个菜单项
     *
     * @return
     * @throws Exception
     */
    public MenuButton createbutton2() throws Exception {
        MenuButton btn = new MenuButton();
        btn.setName("预约开店");
        btn.setType(EventType.view);
        btn.setUrl("http://shop.xiongmaoxingchu.com/#/orderShop");


        MenuButton menuButton2 = new MenuButton();
        menuButton2.setType(EventType.view);
        menuButton2.setName("测试");
        menuButton2.setUrl("http://www.okche.cn/czb-mob/Introduction.html");

        MenuButton menuButton3 = new MenuButton();
        menuButton3.setType(EventType.view);
        menuButton3.setName("测试");
        menuButton3.setUrl("http://www.okche.cn/ContactUs.html");

        MenuButton menuButton4 = new MenuButton();
        menuButton4.setType(EventType.view);
        menuButton4.setName("测试");
        menuButton4.setUrl("http://www.okche.cn/recruitment.html");

        MenuButton menuButton5 = new MenuButton();
        menuButton5.setName("测试");
        menuButton5.setType(EventType.view);
        menuButton5.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.rqw.chezhubang");


        ArrayList<MenuButton> menuButtons = new ArrayList<>();
        menuButtons.add(menuButton5);
//        menuButtons.add(menuButton);
        menuButtons.add(menuButton2);
        menuButtons.add(menuButton3);
        menuButtons.add(menuButton4);

        // btn.setSubButton(menuButtons);

        return btn;
    }

    /**
     * 创建第三个菜单项
     *
     * @return
     * @throws Exception
     */

    public MenuButton createbutton3() throws Exception {

        MenuButton allButton = new MenuButton();
        allButton.setName("我的星厨");


        MenuButton menuButton = new MenuButton();
        menuButton.setName("我的报修");

        String authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String redirect_uri = "http://shop.xiongmaoxingchu.com";
        String encode = URLEncoder.encode(redirect_uri, "utf-8");
        String url1 = authorize_url + "?" +
                "appid=" + Config.instance().getAppid() +//*应用唯一标识
                "&redirect_uri=" + encode +//*重定向地址，需要进行UrlEncode
                "&response_type=code" +//*填code
                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                "&state=charts" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                "&connect_redirect=1#wechat_redirect";//*作用未知
        menuButton.setType(EventType.view);
        menuButton.setUrl(url1);

        MenuButton menuButton1 = new MenuButton();
        menuButton1.setName("我的信息");

        String redirect_uri1 = "http://shop.xiongmaoxingchu.com";
        String encode1 = URLEncoder.encode(redirect_uri1, "utf-8");
        String url2 = authorize_url + "?" +
                "appid=" + Config.instance().getAppid() +//*应用唯一标识
                "&redirect_uri=" + encode1 +//*重定向地址，需要进行UrlEncode
                "&response_type=code" +//*填code
                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
                "&state=dashboard" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                "&connect_redirect=1#wechat_redirect";//*作用未知
        menuButton1.setType(EventType.view);
        menuButton1.setUrl(url2);
//-----

        MenuButton menuButton2 = new MenuButton();
        menuButton2.setName("投诉建议");
//
//        String redirect_uri2 = "http://shop.xiongmaoxingchu.com/wechat/indexss.html";
//        String encode2 = URLEncoder.encode(redirect_uri2, "utf-8");
//        String url3 = authorize_url + "?" +
//                "appid=" + Config.instance().getAppid() +//*应用唯一标识
//                "&redirect_uri=" + encode2 +//*重定向地址，需要进行UrlEncode
//                "&response_type=code" +//*填code
//                "&scope=snsapi_userinfo" +//*应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
//                "&state=suggest" +//用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
//                "&connect_redirect=1#wechat_redirect";//*作用未知
        menuButton2.setType(EventType.click);
        menuButton2.setKey("tousu");


        List<MenuButton> list = new ArrayList<>();

        list.add(menuButton);
        list.add(menuButton1);
        list.add(menuButton2);
        allButton.setSubButton(list);

        return allButton;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}