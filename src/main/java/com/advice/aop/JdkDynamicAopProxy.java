package com.advice.aop;


import com.advice.AbstractAopProxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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
		MethodInvocation invocation;
		TargetSource targetSource = advisedSupport.getTargetSource();
		Object targetObject = targetSource.getTargetObject();
		Class targetClass = targetSource.getTargetClass();

		List<Object> chain = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);

		invocation = new ReflectiveMethodInvocation(targetObject, method, args, chain);
		Object proceed = invocation.proceed();
		return proceed;
	}

	@Override
	public Object getProxy() {
		Object proxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{advisedSupport.getTargetSource()
				.getTargetClass()}, this);
		return proxyInstance;
	}
}
