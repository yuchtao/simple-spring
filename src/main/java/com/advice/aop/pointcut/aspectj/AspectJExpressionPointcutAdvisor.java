package com.advice.aop.pointcut.aspectj;

import com.advice.aop.pointcut.Pointcut;
import com.advice.aop.pointcut.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Created by yuch on 2018/6/13.
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
