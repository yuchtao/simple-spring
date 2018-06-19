package com.advice.aopadvices;

import com.advice.aop.advice.before.MethodBeforeAdivce;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/15.
 */
public class BeforeAdviceTest implements MethodBeforeAdivce {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BEFORE_ADVICE: 前置通知...." + method.getName());
    }
}
