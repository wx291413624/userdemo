package com.example.userdemo.config.validation.role;

import com.example.userdemo.excel.vo.UserExcelVo;

import java.lang.annotation.*;
import java.util.List;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-7 下午5:59
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(value = AdminRoleValidation.class)
public @interface AdminRole {
    int id() default 0;

    String[] role() default "";

}
