package com.base.annotation;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ResponseBody
public @interface ApiMethod {
    boolean isLogin() default false;

    boolean isPage() default false;

    boolean isAuth() default false;

    int version() default 0;

    String[] requireParams() default {};
}
