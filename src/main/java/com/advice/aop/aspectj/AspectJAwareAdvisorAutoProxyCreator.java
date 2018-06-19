package com.advice.aop.aspectj;

import com.advice.aop.JdkDynamicAopProxy;
import com.advice.aop.pointcut.aspectj.AspectJExpressionPointcut;
import com.advice.bean.BeanPostProcessor;
import com.advice.aop.AdvisedSupport;
import com.advice.aop.TargetSource;
import com.advice.aop.cglib.Cglib2AopProxy;
import com.advice.aop.pointcut.aspectj.AspectJExpressionPointcutAdvisor;
import com.advice.aop.pointcut.PointcutAdvisor;
import com.advice.bean.factory.AbstractBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuch on 2018/6/13.
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware, BeanPostProcessor {
    public String IS_JDK_PROXY = "jdk";

    private AbstractBeanFactory beanFactory;

    //通过配置jdk和cglib来决定生成的代理类型
    private String proxyTargetClass;

    private List<PointcutAdvisor> pointcutAdvisors = new ArrayList<PointcutAdvisor>();

    private List<Advice> advices = new ArrayList<Advice>();

    private PointcutAdvisor pointcutAdvisor = null;

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
        if (bean instanceof PointcutAdvisor) {
            return bean;
        }
        if (bean instanceof Advice) {
            return bean;
        }


        if (pointcutAdvisors.size() == 0) {
            pointcutAdvisors = beanFactory.getByType(PointcutAdvisor.class);
            if (pointcutAdvisors.size() != 0) {
                pointcutAdvisor = pointcutAdvisors.get(0);
            }
        }

        if (advices.size() == 0) {
            advices = beanFactory.getByType(Advice.class);
        }

        Object proxy = proxyTargetClass.endsWith(IS_JDK_PROXY) ? getJdkDynamicAopProxy(bean, pointcutAdvisor) : getCglibAopProxy(bean, pointcutAdvisor);
        return proxy;
    }

    public Object getJdkDynamicAopProxy(Object bean, PointcutAdvisor pointcutAdvisor) {
        AdvisedSupport advisedSupport = getAdvisedSupport(bean, pointcutAdvisor);
        Object proxy = new JdkDynamicAopProxy(advisedSupport).getProxy();
        return proxy;
    }

    public Object getCglibAopProxy(Object bean, PointcutAdvisor pointcutAdvisor) {
        AdvisedSupport advisedSupport = getAdvisedSupport(bean, pointcutAdvisor);
        Object proxy = new Cglib2AopProxy(advisedSupport).getProxy();
        return proxy;
    }

    public AdvisedSupport getAdvisedSupport(Object bean, PointcutAdvisor pointcutAdvisor) {
        AdvisedSupport advisedSupport = new AdvisedSupport();

        //逻辑错误,应该是不管pointcutAdvisor是怎么样的，都要注册所有的advice
        //if (pointcutAdvisor == null) {
        //    for (Advice advice : advices) {
        //        advisedSupport.addDefaultPointcutAdvisorAdvice(advice);
        //    }
        //} else {
        //    advisedSupport.addAdvisor(pointcutAdvisor);
        //}

        //正确写法
        if (pointcutAdvisor == null) {
            for (Advice advice : advices) {
                advisedSupport.addDefaultPointcutAdvisorAdvice(advice);
            }
        } else {
            if (pointcutAdvisor instanceof AspectJExpressionPointcutAdvisor){
                //此处不能复用这个对象，如果使用这个对象会导致所有的过滤函数都是最后一个函数，应该添加都过滤链的是同一个对象
                AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = (AspectJExpressionPointcutAdvisor) pointcutAdvisor;
                for (Advice advice : advices) {
                    //必须新建对象
                    AspectJExpressionPointcutAdvisor expressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
                    expressionPointcutAdvisor.setExpression(((AspectJExpressionPointcut)aspectJExpressionPointcutAdvisor.getPointcut()).getExpression());
                    expressionPointcutAdvisor.setAdvice(advice);
                    advisedSupport.addAdvisor(expressionPointcutAdvisor);
                }
            }
        }
        TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces()[0]);
        advisedSupport.setTargetSource(targetSource);
        return advisedSupport;
    }
}
