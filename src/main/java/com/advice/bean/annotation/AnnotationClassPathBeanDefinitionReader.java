package com.advice.bean.annotation;

import com.advice.bean.AbstractBeanDefinitionReader;
import com.advice.bean.BeanDefinition;
import com.advice.bean.PropertyValue;
import com.advice.bean.RefBean;
import com.advice.bean.io.Resource;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by yuch on 2018/6/20.
 */
public class AnnotationClassPathBeanDefinitionReader extends AbstractBeanDefinitionReader {
    private HashMap<String, String> components;

    public AnnotationClassPathBeanDefinitionReader(Resource resource) {
        super(resource);
    }

    @Override
    public void loadBeanDefinition() throws Exception {
        String path = getResource().getResourcePath();
        components = ComponentScanner.getComponentClassName(path);
        for (String key : components.keySet()) {
            String className = components.get(key);
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanName(className);
            addPropertyValues(className, beanDefinition);
            getBeanDefinitionMap().put(key, beanDefinition);
        }
    }

    //解析propertyValue
    private void addPropertyValues(String className, BeanDefinition beanDefinition) throws ClassNotFoundException {
        //对象实例
        Class<?> aClass = Class.forName(className);
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Autowire annotation = declaredField.getAnnotation(Autowire.class);
            Value value = declaredField.getAnnotation(Value.class);
            if (annotation != null) {
                String id = annotation.id();
                RefBean refBean = new RefBean(id);
                PropertyValue propertyValue = new PropertyValue(declaredField.getName(), refBean);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (value != null) {
                String fieldValue = value.value();
                PropertyValue propertyValue = new PropertyValue(declaredField.getName(), fieldValue);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
        }
    }
}
