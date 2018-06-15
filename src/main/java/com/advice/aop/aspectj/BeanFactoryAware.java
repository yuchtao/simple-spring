package com.advice.aop.aspectj;

import com.advice.bean.factory.AbstractBeanFactory;

/**
 * Created by yuch on 2018/6/13.
 */
public interface BeanFactoryAware {
    void setBeanFactory(AbstractBeanFactory beanFactory);
}
