package com.advice.aop.pointcut;

import com.advice.aop.pointcut.Advisor;
import com.advice.aop.pointcut.Pointcut;

/**
 * Created by yuch on 2018/6/13.
 */
public interface PointcutAdivsor extends Advisor {
    Pointcut getPointcut();
}
