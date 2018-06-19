package com.advice.aop;

import com.advice.aop.pointcut.*;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuch on 2018/6/10.
 */
public class AdvisedSupport  {
    //代理的目标函数
    private TargetSource targetSource;
    //执行目标函数的过滤函数
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    private List<Advisor> advisors = new LinkedList<Advisor>();

    private AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void addAdvisor(Advisor advisor){
        int pos = this.advisors.size();
        addAdvisor(pos,advisor);
    }

    public void addDefaultPointcutAdvisorAdvice(Advice advice){
        int size = this.advisors.size();
        addDefaultPointcutAdvisorAdvice(size, advice);
    }

    private void addDefaultPointcutAdvisorAdvice(int pos, Advice advice){
        addAdvisor(pos, new DefaultPointcutAdvisor(advice));
    }

    private void addAdvisor(int pos, Advisor advisor){
        this.advisors.add(pos, advisor);
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        return this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(
                this, method, targetClass);
    }
}
