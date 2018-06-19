package com.advice.aop.pointcut;

import org.aopalliance.aop.Advice;

/**
 * Created by yuch on 2018/6/15.
 */
public abstract class AbstractPointcutAdvisor implements PointcutAdvisor {
    private Advice advice;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
