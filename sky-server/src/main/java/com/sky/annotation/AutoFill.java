package com.sky.annotation;

import com.sky.enumeration.OperationType;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cyan
 * @version 1.0
 */
/*
* 自定义注解 用于标识需要自动填充公共字段的操作
* */
@Target(ElementType.METHOD)//注解针对方法
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //数据库操作类型 Update Insert
    OperationType value();

}
