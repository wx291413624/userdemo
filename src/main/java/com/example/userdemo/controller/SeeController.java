package com.example.userdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.userdemo.excel.vo.UserExcelVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.TreeMap;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-21 下午4:58
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@RestController
@RequestMapping(value = "sse")
public class SeeController {

    @RequestMapping(value = "/push", produces = "text/event-stream")
    public String find() {
        Random r = new Random();
        try {
            Thread.sleep(5000); //睡眠5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("asdad", "asdasd");
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) {
        UserExcelVo userExcelVo = new UserExcelVo();
        try {
            Class<?> ss = Class.forName("UserExcelVo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
