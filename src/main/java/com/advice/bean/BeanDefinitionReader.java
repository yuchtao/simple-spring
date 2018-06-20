package com.advice.bean;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by yuch on 2017/8/29.
 */
public interface BeanDefinitionReader {
    void loadBeanDefinition() throws Exception;
}
