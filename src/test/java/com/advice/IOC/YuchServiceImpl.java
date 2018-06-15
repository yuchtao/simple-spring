package com.advice.IOC;

import org.junit.Assert;

/**
 * Created by yuch on 2017/8/29.
 */
public class YuchServiceImpl implements YuchService {

    public HelloBean helloBean;

    public void output(String text){
        Assert.assertNotNull(helloBean);
        System.out.println(text);
    }

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }
}
