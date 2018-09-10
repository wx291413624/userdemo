package com.example.userdemo.controller;


import com.example.userdemo.config.validation.role.AdminRole;
import com.example.userdemo.config.validation.role.AdminRoleValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-8 上午9:07
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Deprecated
public class DeprecatedController {


    /**
     * @param t
     * @param <T>
     * @SafeVarargs 注解只能用在参数长度可变的方法或构造方法上，且方法必须声明为static或final，否则会出现编译错误。
     * 一个方法使用@SafeVarargs注解的前提是，开发人员必须确保这个方法的实现中对泛型类型参数的处理不会引发类型安全问题。
     * jdk1.5  1.6
     */
    @SafeVarargs
    public static <T> void findAll(T... t) {
        for (T u : t) {
            System.out.println("u = " + u);
        }
        return;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DeprecatedController deprecatedController = new DeprecatedController();
        deprecatedController.findAll(1, 2, 3, 2);

        Method main = AsyncController.class.getMethod("main", null);
        AdminRoleValidation annotation = main.getAnnotation(AdminRoleValidation.class);
        AdminRole[] value = annotation.value();
        System.out.println("annotation = " + value[0].role()[0]);

        AsyncController asyncController = new AsyncController();
        //AccessibleTest类中的成员变量为private,故必须进行此操作
        main.setAccessible(true);
        main.invoke(asyncController, null);
    }


}
