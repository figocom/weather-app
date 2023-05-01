package com.figo.weatherapp.aop.annotation;



import com.figo.weatherapp.enums.PermissionEnum;

import java.lang.annotation.*;


@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface CheckAuth {
    PermissionEnum[] permission() default {};
}
