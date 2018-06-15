package com.advice.aop;

/**
 * Created by yuch on 2018/6/10.
 */
public class TargetSource {
    private Class targetClass;
    private Object targetObject;

    public TargetSource(Object targetObject, Class targetClass) {
        this.targetObject = targetObject;
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }
}
