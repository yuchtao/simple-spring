package com.advice.aop.pointcut;

import com.advice.aop.pointcut.MethodMatcher;

import java.lang.reflect.Method;

/**
 * Created by yuch on 2018/6/15.
 */
public class TrueMethodMatcher implements MethodMatcher {
    public static final com.advice.aop.pointcut.TrueMethodMatcher INSTANCE = new com.advice.aop.pointcut.TrueMethodMatcher();

    private TrueMethodMatcher() {
    }

    @Override
    public boolean matches(Method method, Class classmatch) {
        return true;
    }
}
