package com.advice.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by yuch on 2017/8/30.
 */
public class TimeInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("AROUND_ADVICE:BEGIN.... " + invocation.getMethod().getName());
        Object proceed = invocation.proceed();
        System.out.println("AROUND_ADVICE:END.... " + invocation.getMethod().getName());
        return proceed;
    }
}
