package com.advice.aop.advice.after;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/15.
 */
public interface AfterReturningAdivce extends AfterAdivce {

    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}

