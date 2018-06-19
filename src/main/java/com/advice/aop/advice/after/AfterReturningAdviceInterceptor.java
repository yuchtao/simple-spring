package com.advice.aop.advice.after;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by yuch on 2018/6/15.
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor, AfterAdivce {
    private AfterReturningAdivce afterReturningAdivce;

    public AfterReturningAdviceInterceptor(AfterReturningAdivce afterReturningAdivce) {
        this.afterReturningAdivce = afterReturningAdivce;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        afterReturningAdivce.afterReturning(proceed, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return proceed;
    }
}
