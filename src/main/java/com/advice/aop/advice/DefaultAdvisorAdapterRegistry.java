package com.advice.aop.advice;

import com.advice.aop.advice.AdvisorAdapter;
import com.advice.aop.advice.AdvisorAdapterRegistry;
import com.advice.aop.pointcut.Advisor;
import com.advice.aop.advice.after.AfterReturningAdviceAdapter;
import com.advice.aop.advice.before.MethodBeforeAdviceAdapter;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuch on 2018/6/15.
 */
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry {
    private final List<com.advice.aop.advice.AdvisorAdapter> adapters = new ArrayList<com.advice.aop.advice.AdvisorAdapter>(3);

    public DefaultAdvisorAdapterRegistry() {
        registerAdvisorAdapter(new MethodBeforeAdviceAdapter());
        registerAdvisorAdapter(new AfterReturningAdviceAdapter());
    }

    @Override
    public Advisor wrap(Object advice) {
        return null;
    }

    @Override
    public MethodInterceptor[] getInterceptors(Advisor advisor) {
        List<MethodInterceptor> methodInterceptors = new ArrayList<MethodInterceptor>(3);
        Advice advice = advisor.getAdvice();
        if (advice instanceof MethodInterceptor) {
            methodInterceptors.add((MethodInterceptor) advice);
        }

        return new MethodInterceptor[0];
    }

    @Override
    public void registerAdvisorAdapter(AdvisorAdapter adapter) {
        this.adapters.add(adapter);
    }
}
