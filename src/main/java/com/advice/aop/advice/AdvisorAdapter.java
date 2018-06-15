package com.advice.aop.advice;

import com.advice.aop.pointcut.Advisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by yuch on 2018/6/15.
 * Advisor 适配器
 */
public interface AdvisorAdapter {
    boolean supportsAdvice(Advice advice);

    MethodInterceptor getInterceptor(Advisor advisor);
}
