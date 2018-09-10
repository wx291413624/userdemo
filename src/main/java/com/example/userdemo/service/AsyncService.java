package com.example.userdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Author: 夜凉如水般清澈
 * @Date: 18-5-3 上午9:57
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Service
@Slf4j
public class AsyncService {


    @Async("taskExecutor")
    public Future<String> check() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ss");
        return new AsyncResult<String>("hello world !");
    }
}
