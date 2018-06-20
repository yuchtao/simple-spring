package com.advice.bean.io;

import com.advice.bean.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yuch on 2017/8/29.
 */
public class URLResource implements Resource {
    private URL url;

    public URLResource(URL url) {
        this.url = url;
    }

    @Override
    public String getResourcePath() {
        return url.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
