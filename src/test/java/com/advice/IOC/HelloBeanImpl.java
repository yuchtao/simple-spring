package com.advice.IOC;

/**
 * Created by yuch on 2017/8/28.
 */
public class HelloBeanImpl implements HelloBean {

    private String name;

    private YuchService yuchService;

    @Override
    public void helloWorld() {
        yuchService.output(name);
    }

    //配置文件中装配属性时，通过set方法，否则会报错
    //<property name="name" value="Hello World!"></property>
    public void setName(String name) {
        this.name = name;
    }

    public void setYuchService(YuchService yuchService) {
        this.yuchService = yuchService;
    }
}
