package com.advice.bean.annotation.bean;

import com.advice.bean.annotation.Autowire;
import com.advice.bean.annotation.Component;
import com.advice.bean.annotation.Value;

/**
 * Created by yuch on 2017/8/28.
 */
@Component(id = "helloBeanImpl")
public class HelloBeanImpl implements HelloBean {

    @Value(value = "test")
    private String name;

    @Autowire(id = "yuchServiceImpl")
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
