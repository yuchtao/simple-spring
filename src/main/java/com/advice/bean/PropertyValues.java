package com.advice.bean;

import com.advice.bean.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuch on 2017/8/28.
 */
public class PropertyValues {
    List<com.advice.bean.PropertyValue> propertyValues = new ArrayList<com.advice.bean.PropertyValue>();

    public void addPropertyValue(com.advice.bean.PropertyValue propertyValue){
        propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }
}
