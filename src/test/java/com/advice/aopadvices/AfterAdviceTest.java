package com.advice.aopadvices;

import com.advice.aop.advice.after.AfterReturningAdivce;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/15.
 */
public class AfterAdviceTest implements AfterReturningAdivce {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AFTER_ADVICE: 后置通知...." + method.getName());
    }
}
