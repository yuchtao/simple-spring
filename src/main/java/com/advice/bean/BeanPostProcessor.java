package com.advice.bean;

/**
 * Created by yuch on 2017/9/1.
 */
public interface BeanPostProcessor {
    Object postProcessorBeforeInitializtion(Object bean, String beanName);
    Object postProcessorAfterInitializtion(Object bean, String beanName);
}
