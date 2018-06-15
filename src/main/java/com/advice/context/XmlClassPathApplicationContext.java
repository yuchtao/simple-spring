package com.advice.context;

import com.advice.bean.BeanDefinition;
import com.advice.bean.BeanPostProcessor;
import com.advice.bean.factory.AbstractBeanFactory;
import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResourceLoader;
import com.advice.bean.xml.XmlClassPathBeanDefinitionReader;
import com.advice.bean.factory.AutoWireBeanFactory;
import com.advice.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by yuch on 2017/8/30.
 */
public class XmlClassPathApplicationContext implements ApplicationContext {
    private AbstractBeanFactory beanFactory;
    private String url;

    public XmlClassPathApplicationContext(String url) throws Exception {
        this(url, new AutoWireBeanFactory());
    }

    public XmlClassPathApplicationContext(String url, AbstractBeanFactory beanFactory) throws Exception {
        this.beanFactory = beanFactory;
        this.url = url;
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
        Resource resource = new URLResourceLoader().getResourceLoader(url);
        XmlClassPathBeanDefinitionReader xmlClassPath = new XmlClassPathBeanDefinitionReader(resource);
        xmlClassPath.loadBeanDefinition();
        Map<String, BeanDefinition> beanDefinitionMap = xmlClassPath.getBeanDefinitionMap();
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

    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
