package com.example.userdemo.config;

import com.alibaba.fastjson.JSONObject;
import com.example.userdemo.config.validation.AdminTokenValidation;
import com.example.userdemo.config.validation.UserTokenValidation;
import com.example.userdemo.config.vo.ErrorEnum;
import com.example.userdemo.config.vo.ErrorResponse;
import com.example.userdemo.config.vo.UserHolder;
import com.example.userdemo.domain.SysUser;
import com.example.userdemo.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 测试
 */
@Slf4j
@Component
public class AppTokenIntercepter extends HandlerInterceptorAdapter {

    public static final String TOKEN_KEY = "token";
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter(TOKEN_KEY);
        boolean admin = adminTokenCheck(handler, token);
        if (admin) {
            if (StringUtils.isEmpty(token)) {
                responseData(response);
                return false;
            }
            return super.preHandle(request, response, handler);
        }
        boolean user = userTokenCheck(handler, token);
        if (user) {
            if (StringUtils.isEmpty(token)) {
                responseData(response);
                return false;
            }
            //判断 权限
            SysUser sysUser = JSONObject.parseObject(redisUtil.get(token), SysUser.class);
            if (sysUser == null) {
                responseData(response);
                return false;
            }
            UserHolder.setUser(sysUser);
            return super.preHandle(request, response, handler);
        }
        return super.preHandle(request, response, handler);
    }


    private boolean adminTokenCheck(Object handler, String token) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hmethod = (HandlerMethod) handler;
            Method method = hmethod.getMethod();
            AdminTokenValidation annotation = hmethod.getBeanType().getAnnotation(AdminTokenValidation.class);
            if (annotation != null) {
                return true;
            } else {
                AdminTokenValidation annotation1 = method.getAnnotation(AdminTokenValidation.class);
                if (annotation1 != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean userTokenCheck(Object handler, String token) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hmethod = (HandlerMethod) handler;
            Method method = hmethod.getMethod();
            UserTokenValidation annotation = hmethod.getBeanType().getAnnotation(UserTokenValidation.class);
            if (annotation != null) {
                return true;
            } else {
                UserTokenValidation annotation1 = method.getAnnotation(UserTokenValidation.class);
                if (annotation1 != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private static void responseData(HttpServletResponse response) {
        try {
            String s = new ErrorResponse(ErrorEnum.ADDMINERROR.getCode(), ErrorEnum.ADDMINERROR.getMsg()).toJson();
            response.setContentType("text/html; charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.print(s);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
