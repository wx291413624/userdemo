package com.example.userdemo.controller;

import com.example.userdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param request
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "login")
    public Object login(HttpServletRequest request, HttpServletResponse response, String name, String password) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            byte[] decode = Base64.getDecoder().decode(user.toString());
            String s1 = new String(decode, "UTF-8");
            System.out.println("user 111= " + s1);
        }
        String s = Base64.getEncoder().encodeToString("this is session".getBytes());
        session.setAttribute("user", s);
        return userService.login(name, password);
    }

    /**
     * 注册
     *
     * @param name
     * @param netName
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "register")
    public Object register(String name, String netName, String email, String password) {
        return userService.register(name, netName, email, password);
    }


//    /**
//     * 查询用户
//     *
//     * @return
//     */
//    @RequestMapping(value = "findAll")
//    public Object findAllUser(HttpServletRequest request) {
//        String characterEncoding = request.getCharacterEncoding();
//        log.info(characterEncoding);
//        return userService.findAll();
//    }

}
