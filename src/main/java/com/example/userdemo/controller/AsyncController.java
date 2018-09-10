package com.example.userdemo.controller;

import com.example.userdemo.config.validation.role.AdminRole;
import com.example.userdemo.config.validation.role.AdminRoleValidation;
import com.example.userdemo.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: 夜凉如水般清澈
 * @Date: 18-5-3 上午9:56
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Slf4j
@RestController
@RequestMapping(value = "async")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @AdminRole(role = "artist")
    @RequestMapping(value = "async")
    public Object checkMsg() {
        Future<String> check = asyncService.check();
        String s = null;
        try {
            //定时刷新任务   异步使用
            log.info("s = " + check.isDone());
            s = check.get();
            boolean done = check.isDone();
            log.info("done = " + done);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Deprecated
    @AdminRoleValidation(value = {@AdminRole(role = {"1", "2"})})
    public void main() {
        System.out.println("asyncService = 123123");
    }

}
