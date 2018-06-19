package com.advice.bean.factory;

import com.advice.bean.BeanDefinition;
import com.advice.bean.BeanPostProcessor;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuch on 2017/8/28.
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        //注册的时候不适用doCreate方法初始化对象，在最后需要使用的时候在初始化对象，类似于懒加载
        //Object bean = doCreateBean(beanDefinition);
        //beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }

    //建议使用泛型来使用
    public List<Object> getByTypeOld(Class classType){
        List<Object> list = new ArrayList<Object>();
        for (String beanName : beanDefinitionMap.keySet()) {
            if (classType.isAssignableFrom(beanDefinitionMap.get(beanName).getBeanClass())){
                list.add(getBean(beanName));
            }
        }
        return list;
    }

    //使用泛型，比上面的返回Object用方便很多
    public <T> List<T> getByType(Class<T> classType){
        List<T> list = new ArrayList<T>();
        for (String beanName : beanDefinitionMap.keySet()) {
            if (classType.isAssignableFrom(beanDefinitionMap.get(beanName).getBeanClass())){
                list.add((T)getBean(beanName));
            }
        }
        return list;
    }

    @Override
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        Object bean = beanDefinition.getBean();
        if (null == bean){
            //如果判断为空，则再注入进去
            bean = doCreateBean(beanDefinition);
            bean = InitializtionBean(name, bean);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    protected Object InitializtionBean(String name, Object object){
        Object bean = object;
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessorBeforeInitializtion(object,name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessorAfterInitializtion(object,name);
        }
        return bean;
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
