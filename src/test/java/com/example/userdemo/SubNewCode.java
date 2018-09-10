package com.example.userdemo;

import lombok.Data;

/**
 * @Author: 夜凉如水般清澈
 * 18-7-9 下午2:47
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Data
public class SubNewCode {
    private static final NewTest newTest = new NewTest("Ted", "av1232");
    private static final NewTest newTest1 = new NewTest("Teds", "av123dd2");

    public static void main(String[] args) {
        System.out.println("subNewCode = " + SubNewCode.newTest.getCode());
    }
}
