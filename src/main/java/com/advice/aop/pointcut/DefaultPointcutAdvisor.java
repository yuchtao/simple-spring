package com.advice.aop.pointcut;

import com.advice.aop.pointcut.AbstractPointcutAdvisor;
import com.advice.aop.pointcut.Pointcut;
import org.aopalliance.aop.Advice;

/**
 * Created by yuch on 2018/6/15.
 */
public class DefaultPointcutAdvisor extends AbstractPointcutAdvisor {
    private com.advice.aop.pointcut.Pointcut pointcut = com.advice.aop.pointcut.Pointcut.TRUE;

    public DefaultPointcutAdvisor(Advice advice) {
        this(com.advice.aop.pointcut.Pointcut.TRUE, advice);
    }

    public DefaultPointcutAdvisor(com.advice.aop.pointcut.Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        setAdvice(advice);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
