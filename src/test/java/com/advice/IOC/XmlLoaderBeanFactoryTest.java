package com.advice.IOC;

import com.advice.bean.factory.AbstractBeanFactory;
import com.advice.bean.factory.AutoWireBeanFactory;
import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResourceLoader;
import com.advice.bean.xml.XmlClassPathBeanDefinitionReader;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by yuch on 2017/8/29.
 */
public class XmlLoaderBeanFactoryTest {

    @Test
    public void test() throws Exception {
        Resource resource = new URLResourceLoader().getResourceLoader("yuch.xml");
        XmlClassPathBeanDefinitionReader xmlClassPath = new XmlClassPathBeanDefinitionReader(resource);
        xmlClassPath.loadBeanDefinition();

        AbstractBeanFactory beanFactory = new AutoWireBeanFactory();
        for (String s : xmlClassPath.getBeanDefinitionMap().keySet()) {
            beanFactory.registerBeanDefinition(s, xmlClassPath.getBeanDefinitionMap().get(s));
        }

        HelloBeanImpl helloWorldService = (HelloBeanImpl) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

    @Test
    public void test1() throws Exception {
        Resource resource = new URLResourceLoader().getResourceLoader("yuch.xml");
        XmlClassPathBeanDefinitionReader xmlClassPath = new XmlClassPathBeanDefinitionReader(resource);
        xmlClassPath.loadBeanDefinition();

        AbstractBeanFactory beanFactory = new AutoWireBeanFactory();
        for (String s : xmlClassPath.getBeanDefinitionMap().keySet()) {
            beanFactory.registerBeanDefinition(s, xmlClassPath.getBeanDefinitionMap().get(s));
        }

        YuchServiceImpl yuchService = (YuchServiceImpl) beanFactory.getBean("yuchService");
    }
}
