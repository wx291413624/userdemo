package com.example.userdemo.config.validation.role;

import java.lang.annotation.*;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-7 下午5:56
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminRoleValidation {
    AdminRole[] value();
}
