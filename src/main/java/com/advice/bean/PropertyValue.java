package com.advice.bean;

/**
 * Created by yuch on 2017/8/28.
 */
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
