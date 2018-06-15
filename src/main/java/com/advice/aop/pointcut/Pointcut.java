package com.advice.aop.pointcut;

import com.advice.aop.pointcut.ClassMatcher;
import com.advice.aop.pointcut.MethodMatcher;
import com.advice.aop.pointcut.TruePointcut;

/**
 * Created by yuch on 2018/6/13.
 */
public interface Pointcut {
    ClassMatcher getClassMatcher();

    MethodMatcher getMethodMatcher();

    com.advice.aop.pointcut.Pointcut TRUE = TruePointcut.INSTANCE;
}
