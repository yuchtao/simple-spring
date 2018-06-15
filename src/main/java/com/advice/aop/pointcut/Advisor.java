package com.advice.aop.pointcut;

import org.aopalliance.aop.Advice;

/**
 * Created by yuch on 2018/6/13.
 */
public interface Advisor {
    Advice getAdvice();
}
