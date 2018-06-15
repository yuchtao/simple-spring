package com.advice.aop.advice.before;

import com.advice.aop.advice.before.MethodBeforeAdivce;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by yuch on 2018/6/15.
 */
public class MethodBeforeAdivceInterceptor implements MethodInterceptor {
    private com.advice.aop.advice.before.MethodBeforeAdivce methodBeforeAdivce;

    public MethodBeforeAdivceInterceptor(MethodBeforeAdivce methodBeforeAdivce) {
        this.methodBeforeAdivce = methodBeforeAdivce;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.methodBeforeAdivce.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
