package com.advice.IOC;

import com.advice.bean.BeanDefinition;
import com.advice.bean.PropertyValue;
import com.advice.bean.PropertyValues;
import com.advice.bean.factory.AbstractBeanFactory;
import com.advice.bean.factory.AutoWireBeanFactory;
import org.junit.Test;

/**
 * Created by yuch on 2017/8/28.
 */
public class BeanFactoryTest {

    @Test
    public void test1(){
        //工厂类
        AbstractBeanFactory beanFactory = new AutoWireBeanFactory();

        //属性类
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","nameField"));

        //定义bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setPropertyValues(propertyValues);
        beanDefinition.setBeanName("com.test.IOC.HelloBeanImpl");

        //注册Bean
        beanFactory.registerBeanDefinition("helloWorld", beanDefinition);

        //得到Bean
        HelloBeanImpl helloWorld = (HelloBeanImpl) beanFactory.getBean("helloWorld");
        helloWorld.helloWorld();
    }
}
