package com.advice.aop.cglib;

import com.advice.aop.AdvisedSupport;
import com.advice.aop.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

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
        Object targetObject = advisedSupport.getTargetSource().getTargetObject();
        if (advisedSupport.getMethodMatcher()!= null && advisedSupport.getMethodMatcher().matches(method, targetObject.getClass())){
            org.aopalliance.intercept.MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
        }else {
            return method.invoke(proxy, args);
        }
    }
}
