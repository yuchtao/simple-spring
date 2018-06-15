package com.advice.IOC;

import com.advice.context.XmlClassPathApplicationContext;
import org.junit.Test;

/**
 * Created by yuch on 2017/8/30.
 */
public class ApplicationContextTest {

    @Test
    public void test1() throws Exception {
        XmlClassPathApplicationContext applicationContext = new XmlClassPathApplicationContext("yuch.xml");
        HelloBean yuchService = (HelloBean)applicationContext.getBean("helloBeanImpl");
        yuchService.helloWorld();
    }
}
