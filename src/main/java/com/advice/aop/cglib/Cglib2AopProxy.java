package com.advice.aop.cglib;

import com.advice.AbstractAopProxy;
import com.advice.aop.AdvisedSupport;
import com.advice.aop.cglib.CglibMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by yuch on 2018/6/14.
 */
public class Cglib2AopProxy extends AbstractAopProxy {
    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback(new CglibMethodInterceptor(advisedSupport));
        return enhancer.create();
    }
}
