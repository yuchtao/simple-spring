package com.advice.bean.xml;

import com.advice.bean.AbstractBeanDefinitionReader;
import com.advice.bean.BeanDefinition;
import com.advice.bean.PropertyValue;
import com.advice.bean.RefBean;
import com.advice.bean.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by yuch on 2017/8/29.
 */
public class XmlClassPathBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlClassPathBeanDefinitionReader(Resource resource){
        super(resource);
    }

    @Override
    public void loadBeanDefinition() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parse = documentBuilder.parse(getResource().getInputStream());
        parseInputStream(parse);
    }

    //获取根目录
    private void parseInputStream(Document document){
        Element root = document.getDocumentElement();
        parseRoot(root);
    }

    //循环根目录
    private void parseRoot(Element root){
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE){
                getBeanDefinition(item);
            }
        }
    }

    //解析bean
    private void getBeanDefinition(Node bean){
        String name = bean.getAttributes().getNamedItem("name").getNodeValue();
        String className = bean.getAttributes().getNamedItem("class").getNodeValue();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanName(className);
        addPropertyValues(bean, beanDefinition);
        getBeanDefinitionMap().put(name, beanDefinition);
    }

    //解析propertyValue
    private void addPropertyValues(Node bean, BeanDefinition beanDefinition){
        NodeList childNodes = bean.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE){
                String name = item.getAttributes().getNamedItem("name").getNodeValue();
                Node value = item.getAttributes().getNamedItem("value");
                PropertyValue propertyValue = null;
                //判断是普通类型，还是bean
                if (null != value){
                    propertyValue = new PropertyValue(name,value.getNodeValue());
                }else {
                    String ref = item.getAttributes().getNamedItem("ref").getNodeValue();
                    RefBean refBean = new RefBean(ref);
                    propertyValue = new PropertyValue(name, refBean);
                }
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
        }
    }
}
