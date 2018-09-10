package com.example.userdemo;

import com.example.userdemo.controller.AsyncController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserdemoApplicationTests {
    @Autowired
    private AsyncController asyncController;

    @Test

    public void contextLoads() {
        asyncController.main();
    }

    public static void main(String[] args) {
        int a = 2;//阶乘数
        int sum = 0; //求和
        for (int i = 1; i <= a; i++) {//阶乘分组
            int sub = 1;
            System.out.println("sub = " + sub);
            for (int j = 1; j <= i; j++) {//乘积计算
                // 1*2*3*4
                sub *= j;
            }
            sum += sub;
        }
        System.out.println("sum = " + sum);
    }
}
