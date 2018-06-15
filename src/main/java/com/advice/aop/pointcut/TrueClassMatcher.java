package com.advice.aop.pointcut;

import com.advice.aop.pointcut.ClassMatcher;

/**
 * Created by yuch on 2018/6/15.
 */
public class TrueClassMatcher implements ClassMatcher {
    public static final com.advice.aop.pointcut.TrueClassMatcher INSTANCE = new com.advice.aop.pointcut.TrueClassMatcher();

    /**
     * Enforce Singleton pattern.
     */
    private TrueClassMatcher() {
    }

    @Override
    public boolean matches(Class classmatch) {
        return true;
    }
}
