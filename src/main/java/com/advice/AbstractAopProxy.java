package com.advice;

import com.advice.aop.AdvisedSupport;
import com.advice.aop.AopProxy;

/**
 * Created by yuch on 2018/6/14.
 */
public abstract class AbstractAopProxy implements AopProxy {
    public AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}
