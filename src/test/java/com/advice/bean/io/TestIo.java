package com.advice.bean.io;

import com.advice.bean.io.Resource;
import com.advice.bean.io.URLResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuch on 2017/8/29.
 */
public class TestIo {

    @Test
    public void testIO() throws IOException {
        Resource resourceLoader = new URLResourceLoader().getResourceLoader("yuch.xml");
        InputStream inputStream = resourceLoader.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
