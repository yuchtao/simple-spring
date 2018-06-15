package com.advice.aop;

import com.advice.aop.pointcut.Advisor;
import com.advice.aop.pointcut.DefaultPointcutAdvisor;
import com.advice.aop.pointcut.MethodMatcher;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuch on 2018/6/10.
 */
public class AdvisedSupport {
    //代理的目标函数
    private TargetSource targetSource;
    //执行目标函数的过滤函数
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    private List<Advisor> advisors = new LinkedList<Advisor>();

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

    public void addAdvice(Advice advice){
        int size = this.advisors.size();
        addAdvice(size, advice);
    }

    public void addAdvice(int pos, Advice advice){
        addAdvisor(pos, new DefaultPointcutAdvisor(advice));
    }

    public void addAdvisor(int pos, Advisor advisor){
        this.advisors.add(pos, advisor);
    }
}
