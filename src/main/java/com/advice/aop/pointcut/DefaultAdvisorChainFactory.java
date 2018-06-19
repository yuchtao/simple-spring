package com.advice.aop.pointcut;

import com.advice.aop.AdvisedSupport;
import com.advice.aop.advice.AdvisorAdapterRegistry;
import com.advice.aop.advice.GlobalAdvisorAdapterRegistry;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuch on 2018/6/15.
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport advisedSupport, Method method, Class<?> targetClass) {
        List<Object> interceptorList = new ArrayList<Object>(advisedSupport.getAdvisors().size());
        AdvisorAdapterRegistry registry = GlobalAdvisorAdapterRegistry.getInstance();
        for (Advisor advisor : advisedSupport.getAdvisors()) {
            PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
            if (pointcutAdvisor.getPointcut().getClassMatcher().matches(targetClass)) {
                MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
                MethodMatcher methodMatcher = pointcutAdvisor.getPointcut().getMethodMatcher();
                if (methodMatcher.matches(method, targetClass)) {
                    interceptorList.addAll(Arrays.asList(interceptors));
                }
            }
        }
        return interceptorList;
    }
}
