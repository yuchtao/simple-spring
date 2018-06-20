package com.advice.bean.xml;

import com.advice.bean.BeanDefinition;
import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResourceLoader;
import com.advice.bean.xml.XmlClassPathBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yuch on 2017/8/29.
 */
public class XmlTest {

    @Test
    public void test() throws Exception {
        Resource resource = new URLResourceLoader().getResourceLoader("yuch.xml");
        com.advice.bean.xml.XmlClassPathBeanDefinitionReader xmlClassPath = new XmlClassPathBeanDefinitionReader(resource);
        xmlClassPath.loadBeanDefinition();
        Map<String, BeanDefinition> beanDefinitionMap = xmlClassPath.getBeanDefinitionMap();
        Assert.assertNotNull(beanDefinitionMap);
    }
}
