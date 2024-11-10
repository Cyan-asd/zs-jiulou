package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author cyan
 * @version 1.0
 */
/*
* 自定义切面 实现公共字段自动填充
* */

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /*
    * 切入点
    * */
    @Pointcut("execution(* com.sky.mapper.*.*(..))&& @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {}

    //前置通知 在通知中进行公共字段的赋值
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("AutoFillPointCut");
        //获取当前被拦截对象的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autofill = signature.getMethod().getAnnotation(AutoFill.class);//获取注解对象
        OperationType operationType = autofill.value();//数据库操作类型

        //获取拦截对象的参数 实体对象 默认放在第一个
        Object[] args = joinPoint.getArgs();
        if(args==null || args.length==0){
            return;
        }
        Object entity = args[0];

        //获取准备赋值的数据
        LocalDateTime time = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();

        //根据操作类型的不同进行操作


        if(operationType==OperationType.INSERT){
            try {
                Method setCreateTime = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setCreateUser = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);

                setCreateTime.invoke(entity,time);
                setCreateUser.invoke(entity,id);
                setUpdateTime.invoke(entity,time);
                setUpdateUser.invoke(entity,id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else if(operationType==OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity, time);
                setUpdateUser.invoke(entity, id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
