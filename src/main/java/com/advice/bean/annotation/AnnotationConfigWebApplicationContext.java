package com.advice.bean.annotation;

import com.advice.bean.BeanDefinition;
import com.advice.bean.BeanPostProcessor;
import com.advice.bean.factory.AbstractBeanFactory;
import com.advice.bean.factory.AutoWireBeanFactory;
import com.advice.bean.io.AnnotationResource;
import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResourceLoader;
import com.advice.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by yuch on 2018/6/20.
 */
public class AnnotationConfigWebApplicationContext implements ApplicationContext {
    private AbstractBeanFactory beanFactory;
    private String packageName;

    public AnnotationConfigWebApplicationContext(String packageName) {
        this(new AutoWireBeanFactory(), packageName);
    }

    public AnnotationConfigWebApplicationContext(AbstractBeanFactory beanFactory, String packageName) {
        this.beanFactory = beanFactory;
        this.packageName = packageName;
        refresh();
    }

    public void refresh(){
        try {
            registerBeanFactory();
            initBeanPostProcessor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerBeanFactory() throws Exception {
        Resource resource = new AnnotationResource(packageName);
        AnnotationClassPathBeanDefinitionReader beanDefinitionReader = new AnnotationClassPathBeanDefinitionReader(resource);
        beanDefinitionReader.loadBeanDefinition();
        Map<String, BeanDefinition> beanDefinitionMap = beanDefinitionReader.getBeanDefinitionMap();
        for (String key : beanDefinitionMap.keySet()) {
            beanFactory.registerBeanDefinition(key, beanDefinitionMap.get(key));
        }
    }

    public void initBeanPostProcessor(){
        //优先初始化，后面使用getBean方法时，可以生成相应的代理类
        List<BeanPostProcessor> byType = beanFactory.getByType(BeanPostProcessor.class);
        for (BeanPostProcessor type : byType) {
            beanFactory.addBeanPostProcessor(type);
        }
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
