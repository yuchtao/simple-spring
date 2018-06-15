package com.advice.aop;

import com.advice.IOC.HelloBeanImpl;
import com.advice.aop.pointcut.aspectj.AspectJExpressionPointcut;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuch on 2018/6/13.
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassMatcher() throws Exception{
        String expression = "execution(* com.yuch.IOC.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassMatcher().matches(HelloBeanImpl.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void MethodMatcher() throws Exception{
        String expression = "execution(* com.yuch.IOC.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloBeanImpl.class.getMethod("helloWorld"), HelloBeanImpl.class);
        Assert.assertTrue(matches);
    }
}
