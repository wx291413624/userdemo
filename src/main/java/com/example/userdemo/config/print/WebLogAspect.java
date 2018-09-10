package com.example.userdemo.config.print;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut(value = "execution(public * com.example.userdemo.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容

        StringBuilder logSt = new StringBuilder("");

        logSt.append("\n\n\t\tCONTROLLER_CLASS_METHOD : ").append(joinPoint.getSignature().getDeclaringTypeName())
                .append(".")
                .append(joinPoint.getSignature().getName())
                .append("\n\t\t")
                .append("CONTROLLER_ARGS : ")
                .append(Arrays.toString(joinPoint.getArgs()))
                .append("\n\t\t")
                .append("IP : ")
                .append(request.getRemoteAddr())
                .append("\n\t\t")
                .append("URL : ")
                .append(request.getRequestURL().toString())
                .append("\n\t\t")
                .append("HTTP_METHOD : ")
                .append(request.getMethod())
                .append("\n\t\t");
        log.info(logSt + "");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        try {
            if (ret.toString().startsWith("<xml>")) {
                log.info("RESPONSE : \n" + ret);
            } else {
                log.info("RESPONSE : \n" + FormatUtil.formatJson(JSONObject.toJSONStringWithDateFormat(ret, "yyyy-MM-dd HH:mm:ss")));
            }
        } catch (Exception e) {
            log.info("RESPONSE : " + ret);
        }


    }


}