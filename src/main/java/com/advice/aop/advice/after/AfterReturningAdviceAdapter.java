package com.advice.aop.advice.after;

import com.advice.aop.advice.AdvisorAdapter;
import com.advice.aop.advice.after.AfterReturningAdivce;
import com.advice.aop.advice.after.AfterReturningAdviceInterceptor;
import com.advice.aop.pointcut.Advisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by yuch on 2018/6/15.
 */
public class AfterReturningAdviceAdapter implements AdvisorAdapter {

    @Override
    public boolean supportsAdvice(Advice advice) {
        return advice instanceof com.advice.aop.advice.after.AfterReturningAdivce;
    }

    @Override
    public MethodInterceptor getInterceptor(Advisor advisor) {
        com.advice.aop.advice.after.AfterReturningAdivce advice = (AfterReturningAdivce) advisor.getAdvice();
        return new AfterReturningAdviceInterceptor(advice);
    }
}
