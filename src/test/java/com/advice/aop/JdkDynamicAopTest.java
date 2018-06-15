package com.advice.aop;

import com.advice.IOC.HelloBean;
import com.advice.context.XmlClassPathApplicationContext;
import org.junit.Test;

/**
 * Created by yuch on 2018/6/10.
 */
public class JdkDynamicAopTest {

    @Test
    public void test1(){
        try {
            XmlClassPathApplicationContext xmlClassPathApplicationContext = new XmlClassPathApplicationContext("yuch.xml");
            HelloBean helloBeanImpl = (HelloBean) xmlClassPathApplicationContext.getBean("helloBeanImpl");
            helloBeanImpl.helloWorld();

            //com.advice.aop.AdvisedSupport advisedSupport = new AdvisedSupport();
            //com.advice.aop.TargetSource targetSource = new TargetSource(helloBeanImpl, HelloBean.class);
            //
            //TimeInterceptor timeInterceptor = new TimeInterceptor();
            //advisedSupport.setTargetSource(targetSource);
            //advisedSupport.setMethodInterceptor(timeInterceptor);
            //
            //Object proxy = new JdkDynamicAopProxy(advisedSupport).getProxy();
            //HelloBean helloWorld = (HelloBean) proxy;
            //helloWorld.helloWorld();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
