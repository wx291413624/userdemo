package com.example.userdemo.notes.rtTest.java.langtest.ref;

import org.springframework.context.annotation.Bean;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-8 下午2:50
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
public class test {
    public static void mains(String[] args) {
        SoftReference<St> bean = new SoftReference<St>(new St("name", 10));
        System.out.println(bean.get());// “name:10”
        System.out.println("bean = " + bean.get());
        WeakReference<St> weakReference = new WeakReference<St>(new St("name", 10));
        System.out.println("weakReference = " + weakReference.get());
        System.out.println("weakReference = " + weakReference.get()
        );
    }

    public static void main(String[] args) {
        ReferenceQueue<String> refQueue = new ReferenceQueue<String>();
        PhantomReference<String> referent = new PhantomReference<String>(
                new String("T"), refQueue);
        System.out.println(referent.get());// null
        System.gc();
        System.runFinalization();
        System.out.println(refQueue.poll() == referent); //true
    }
}
