package com.advice.bean.io;

import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResource;

import java.net.URL;

/**
 * Created by yuch on 2017/8/29.
 */
public class URLResourceLoader {

    public Resource getResourceLoader(String url){
        URL resource = this.getClass().getClassLoader().getResource(url);
        return  new URLResource(resource);
    }

}
