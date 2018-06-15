package com.advice.aop.advice.before;

import com.advice.aop.advice.before.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/15.
 */
public interface MethodBeforeAdivce extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
