package com.advice.aop;

import com.advice.bean.BeanPostProcessor;

/**
 * Created by yuch on 2018/6/13.
 */
public class BeanPostProcessorTest implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitializtion(Object bean, String beanName) {
        System.out.println(beanName+"  初始化前!!");
        return bean;
    }

    @Override
    public Object postProcessorAfterInitializtion(Object bean, String beanName) {
        System.out.println(beanName+"  初始化后!!");
        return bean;
    }
}
