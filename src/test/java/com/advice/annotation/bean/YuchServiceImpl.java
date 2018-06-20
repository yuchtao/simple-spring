package com.advice.annotation.bean;

import com.advice.bean.annotation.Component;

/**
 * Created by yuch on 2017/8/29.
 */
@Component(id = "yuchServiceImpl")
public class YuchServiceImpl implements YuchService {

    public void output(String text){
        System.out.println(text);
    }
}
