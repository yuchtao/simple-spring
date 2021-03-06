package com.advice.aop.cglib;

import com.advice.aop.AdvisedSupport;
import com.advice.aop.ReflectiveMethodInvocation;
import com.advice.aop.TargetSource;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yuch on 2018/6/14.
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    private AdvisedSupport advisedSupport;

    public CglibMethodInterceptor(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MethodInvocation invocation;
        TargetSource targetSource = advisedSupport.getTargetSource();
        Object targetObject = targetSource.getTargetObject();
        Class targetClass = targetSource.getTargetClass();

        List<Object> chain = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);

        invocation = new ReflectiveMethodInvocation(targetObject, method, args, chain);
        Object proceed = invocation.proceed();
        return proceed;
    }
}
