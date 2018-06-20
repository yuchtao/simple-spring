package com.advice.bean.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuch on 2018/6/20.
 */
public class AnnotationResource implements Resource {
    private String packageName;

    public AnnotationResource(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getResourcePath() throws IOException {
        return this.packageName;
    }
}
