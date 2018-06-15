package com.advice.bean.factory;

import com.advice.aop.aspectj.BeanFactoryAware;
import com.advice.bean.BeanDefinition;
import com.advice.bean.PropertyValue;
import com.advice.bean.RefBean;
import com.advice.bean.factory.AbstractBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yuch on 2017/8/28.
 */
public class AutoWireBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        Object object = createBean(beanDefinition);
        beanDefinition.setBean(object);
        addPropertyValue(object, beanDefinition);
        return object;
    }

    protected Object createBean(BeanDefinition beanDefinition) {
        Object object = null;
        try {
            object = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    protected void addPropertyValue(Object object, BeanDefinition beanDefinition) {
        //识别是否是BeanFactoryAware，将Beanfactory注入进去，生成代理用
        if ( object instanceof BeanFactoryAware) {
            ((BeanFactoryAware) object).setBeanFactory(this);
        }
            
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            //set方法注入
            try {
                String methodName = propertyValue.getName();
                //想初始化关联的对象 必须先初始化对象，才能得到set方法
                if (propertyValue.getValue() instanceof RefBean) {
                    Object beanInit = getBean(((RefBean) propertyValue.getValue()).getName());
                    // set方法如果参数是接口，这里得到的实例是接口的实现类那么就会报错
                    Method method = object.getClass().getMethod("set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1), beanInit.getClass().getInterfaces()[0]);
                    method.invoke(object, beanInit);
                } else {
                    Method method = object.getClass().getMethod("set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1), propertyValue.getValue().getClass());
                    method.invoke(object, propertyValue.getValue());
                }
            } catch (Exception e) {
                //出现错误后，执行字段注入
                try {
                    //如果字段是父类的字段那么获取会报错
                    Field declaredField = object.getClass().getDeclaredField(propertyValue.getName());
                    System.err.println(object.getClass().getName()+"的属性"+declaredField.getName()+" 初始化失败！！");
                    declaredField.setAccessible(true);
                    if (propertyValue.getValue() instanceof RefBean) {
                        RefBean refBean = (RefBean) propertyValue.getValue();
                        Object bean = getBean(refBean.getName());
                        declaredField.set(object, bean);
                    } else {
                        declaredField.set(object, propertyValue.getValue());
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
}
