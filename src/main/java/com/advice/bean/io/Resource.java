package com.advice.bean.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuch on 2017/8/29.
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
