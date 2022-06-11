package com.example.myapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这里仅仅是运行时注解，编译时注解是通过process类获取element节点信息，动态生成Java文件，导入注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Description {
    String author() default "张三";
    String desc() default "他的具体表述";
    int age () default 18;
}
