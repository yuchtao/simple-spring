package com.advice.annotation;

import com.advice.annotation.bean.HelloBean;
import com.advice.bean.annotation.AnnotationConfigWebApplicationContext;
import org.junit.Test;

/**
 * Created by yuch on 2018/6/14.
 */
public class AnnotationIOCTest {

    @Test
    public void test1() {
        try {
            AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext("com.advice.annotation.bean");
            HelloBean helloBeanImpl = (HelloBean) webApplicationContext.getBean("helloBeanImpl");
            helloBeanImpl.helloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
