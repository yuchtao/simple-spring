package com.advice.bean.io;

import java.net.URL;

/**
 * Created by yuch on 2017/8/29.
 */
public class URLResourceLoader {

    public Resource getResourceLoader(String url){
        URL resource = this.getClass().getClassLoader().getResource(url);
        return  new URLResource(resource);
    }

    public static void main(String[] args) {
        URLResourceLoader urlResourceLoader = new URLResourceLoader();
        Resource resourceLoader = urlResourceLoader.getResourceLoader("yuch.xml");
        System.out.println();
    }

}
