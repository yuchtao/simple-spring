package com.advice.bean;


import com.advice.bean.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuch on 2017/8/29.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Map<String, BeanDefinition> beanDefinitionMap = null;

    private Resource resource;

    public AbstractBeanDefinitionReader(Resource resource){
        beanDefinitionMap = new HashMap<String, BeanDefinition>();
        this.resource = resource;
    }

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
