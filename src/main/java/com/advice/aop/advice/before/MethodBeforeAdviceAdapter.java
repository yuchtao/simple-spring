package com.advice.aop.advice.before;

import com.advice.aop.advice.AdvisorAdapter;
import com.advice.aop.pointcut.Advisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by yuch on 2018/6/15.
 */
public class MethodBeforeAdviceAdapter implements AdvisorAdapter {

    @Override
    public boolean supportsAdvice(Advice advice) {
        return advice instanceof MethodBeforeAdivce;
    }

    @Override
    public MethodInterceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdivce methodBeforeAdivce =(MethodBeforeAdivce) advisor.getAdvice();
        return new MethodBeforeAdivceInterceptor(methodBeforeAdivce);
    }
}
