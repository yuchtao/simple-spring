package com.advice.aop;


import com.advice.AbstractAopProxy;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于jdk的动态代理
 * 
 * @author yihua.huang@dianping.com
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
    	super(advisedSupport);
    }

    @Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object targetObject = advisedSupport.getTargetSource().getTargetObject();
		if (advisedSupport.getMethodMatcher() != null && advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())){
			MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
		} else {
    		return method.invoke(targetObject, args);
		}
	}

	@Override
	public Object getProxy() {
		Object proxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{advisedSupport.getTargetSource()
				.getTargetClass()}, this);
		return proxyInstance;
	}
}
