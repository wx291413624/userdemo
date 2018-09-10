package com.example.userdemo.notes.rtTest.java.langtest;

import java.io.IOException;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-7 下午5:05
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class Student implements Comparable {

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Student student = new Student();
        Object clone = student.clone();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
