package com.advice.aop.pointcut;

import com.advice.aop.pointcut.ClassMatcher;
import com.advice.aop.pointcut.MethodMatcher;
import com.advice.aop.pointcut.Pointcut;
import com.advice.aop.pointcut.TrueClassMatcher;
import com.advice.aop.pointcut.TrueMethodMatcher;

/**
 * Created by yuch on 2018/6/15.
 */
public class TruePointcut implements Pointcut {

    public static final com.advice.aop.pointcut.TruePointcut INSTANCE = new com.advice.aop.pointcut.TruePointcut();

    /**
     * Enforce Singleton pattern.
     */
    private TruePointcut() {
    }

    @Override
    public ClassMatcher getClassMatcher() {
        return TrueClassMatcher.INSTANCE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return TrueMethodMatcher.INSTANCE;
    }

    /**
     * Required to support serialization. Replaces with canonical
     * instance on deserialization, protecting Singleton pattern.
     * Alternative to overriding {@code equals()}.
     */
    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Pointcut.TRUE";
    }
}
