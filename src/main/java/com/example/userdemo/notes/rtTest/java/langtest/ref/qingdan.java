package com.example.userdemo.notes.rtTest.java.langtest.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-8 下午3:05
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class qingdan {
    public static void main(String[] args) {
        St name = new St("name", 10);
        System.gc();
        System.runFinalization();
        System.out.println(name);// “name:10”

        SoftReference<St> bean = new SoftReference<St>(new St("name", 10));
        System.gc();
        System.runFinalization();
        System.out.println(bean.get());// “name:10”

        WeakReference<St> St = new WeakReference<St>(new St("name", 10));
        System.gc();
        System.runFinalization();
        System.out.println(St.get());// “null”


    }

}
