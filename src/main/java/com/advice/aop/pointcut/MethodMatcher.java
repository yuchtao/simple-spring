package com.advice.aop.pointcut;

import com.advice.aop.pointcut.TrueMethodMatcher;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/13.
 */
public interface MethodMatcher {
    boolean matches(Method method, Class classmatch);

    com.advice.aop.pointcut.MethodMatcher TRUE = TrueMethodMatcher.INSTANCE;
}
