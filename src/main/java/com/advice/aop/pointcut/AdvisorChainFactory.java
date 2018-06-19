package com.advice.aop.pointcut;

import com.advice.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yuch on 2018/6/15.
 */
public interface AdvisorChainFactory {
    List<Object> getInterceptorsAndDynamicInterceptionAdvice(
            AdvisedSupport advisedSupport, Method method, Class<?> targetClass);
}
