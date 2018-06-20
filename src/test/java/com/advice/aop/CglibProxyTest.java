package com.advice.aop;

import com.advice.IOC.HelloBean;
import com.advice.context.XmlClassPathApplicationContext;
import org.junit.Test;

/**
 * Created by yuch on 2018/6/14.
 * aop测试类，jdk和cglib方式主要是由AspectJAwareAdvisorAutoProxyCreator的proxyTargetClass属性决定的
 */
public class CglibProxyTest {

    @Test
    public void test1() {
        try {
            XmlClassPathApplicationContext xmlClassPathApplicationContext = new XmlClassPathApplicationContext("yuch.xml");
            HelloBean helloBeanImpl = (HelloBean) xmlClassPathApplicationContext.getBean("helloBeanImpl");
            helloBeanImpl.helloWorld();

            //AdvisedSupport advisedSupport = new AdvisedSupport();
            //TargetSource targetSource = new TargetSource(helloBeanImpl, HelloBean.class);
            //
            //TimeInterceptor timeInterceptor = new TimeInterceptor();
            //advisedSupport.setTargetSource(targetSource);
            //advisedSupport.setMethodInterceptor(timeInterceptor);
            //
            //advisedSupport.setMethodMatcher(new MethodMatcher() {
            //    @Override
            //    public boolean matches(Method method, Class classmatch) {
            //        return true;
            //    }
            //});
            //
            //Object proxy = new Cglib2AopProxy(advisedSupport).getProxy();
            //HelloBean helloBean = (HelloBean) proxy;
            //helloBean.helloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
