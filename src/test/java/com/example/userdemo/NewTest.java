package com.example.userdemo;

import lombok.Data;

/**
 * @Author: 夜凉如水般清澈
 * 18-7-9 下午2:46
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Data
public class NewTest {
    private String name;
    private String code;

    public NewTest(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
