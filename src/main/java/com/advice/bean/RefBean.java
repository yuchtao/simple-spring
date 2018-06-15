package com.advice.bean;

/**
 * Created by yuch on 2017/8/29.
 */
public class RefBean {
    private String name;
    private Object value;

    public RefBean(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
