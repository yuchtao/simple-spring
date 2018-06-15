package com.advice.aop.aspectj;

import com.advice.aop.JdkDynamicAopProxy;
import com.advice.aop.aspectj.BeanFactoryAware;
import com.advice.bean.BeanPostProcessor;
import com.advice.aop.AdvisedSupport;
import com.advice.aop.TargetSource;
import com.advice.aop.cglib.Cglib2AopProxy;
import com.advice.aop.pointcut.aspectj.AspectJExpressionPointcutAdvisor;
import com.advice.aop.pointcut.PointcutAdivsor;
import com.advice.bean.factory.AbstractBeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuch on 2018/6/13.
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware, BeanPostProcessor {
    public  String IS_JDK_PROXY = "jdk";

    private AbstractBeanFactory beanFactory;

    //通过配置jdk和cglib来决定生成的代理类型
    private String proxyTargetClass;

    private List<AspectJExpressionPointcutAdvisor> pointcutAdvisors = new ArrayList<AspectJExpressionPointcutAdvisor>();

    public void setBeanFactory(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setProxyTargetClass(String proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    @Override
    public Object postProcessorBeforeInitializtion(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitializtion(Object bean, String beanName) {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        if (pointcutAdvisors.size() == 0) {
            pointcutAdvisors = beanFactory.getByType(AspectJExpressionPointcutAdvisor.class);
        }

        for (Object pointcutAdvisorTmp : pointcutAdvisors) {
            AspectJExpressionPointcutAdvisor pointcutAdvisor = (AspectJExpressionPointcutAdvisor) pointcutAdvisorTmp;
            if (pointcutAdvisor.getPointcut().getClassMatcher().matches(bean.getClass())) {
                Object proxy = proxyTargetClass.endsWith(IS_JDK_PROXY) ? getJdkDynamicAopProxy(bean, pointcutAdvisor) : getCglibAopProxy(bean, pointcutAdvisor);
                return proxy;
            }
        }
        return bean;
    }

    public Object getJdkDynamicAopProxy(Object bean, PointcutAdivsor pointcutAdvisor) {
        AdvisedSupport advisedSupport = getAdvisedSupport(bean, pointcutAdvisor);
        Object proxy = new JdkDynamicAopProxy(advisedSupport).getProxy();
        return proxy;
    }

    public Object getCglibAopProxy(Object bean, PointcutAdivsor pointcutAdvisor) {
        AdvisedSupport advisedSupport = getAdvisedSupport(bean, pointcutAdvisor);
        Object proxy = new Cglib2AopProxy(advisedSupport).getProxy();
        return proxy;
    }

    public AdvisedSupport getAdvisedSupport(Object bean, PointcutAdivsor pointcutAdvisor) {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor((MethodInterceptor) pointcutAdvisor.getAdvice());
        advisedSupport.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());
        TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces()[0]);
        advisedSupport.setTargetSource(targetSource);
        return advisedSupport;
    }
}
