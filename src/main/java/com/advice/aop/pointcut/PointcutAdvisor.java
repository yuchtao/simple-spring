package com.advice.aop.pointcut;

/**
 * Created by yuch on 2018/6/13.
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
