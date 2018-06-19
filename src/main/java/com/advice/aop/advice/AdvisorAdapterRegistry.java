package com.advice.aop.advice;

import com.advice.aop.pointcut.Advisor;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by yuch on 2018/6/15.
 */
public interface AdvisorAdapterRegistry {
    Advisor wrap(Object advice);

    MethodInterceptor[] getInterceptors(Advisor advisor);

    void registerAdvisorAdapter(AdvisorAdapter adapter);
}
