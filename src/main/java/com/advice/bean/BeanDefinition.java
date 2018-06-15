package com.advice.bean;

import com.advice.bean.PropertyValues;

/**
 * Created by yuch on 2017/8/28.
 */
public class BeanDefinition {
    private Object bean;

    private Class beanClass;

    private String beanName;

    private com.advice.bean.PropertyValues propertyValues = new com.advice.bean.PropertyValues();

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
        try {
            if (beanName != null) {
                this.beanClass = Class.forName(beanName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public com.advice.bean.PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
