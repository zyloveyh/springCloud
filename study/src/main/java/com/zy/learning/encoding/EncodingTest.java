package com.zy.learning.encoding;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodingTest {
    @Test
    public void testURlEncoding() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("我靠你他大爷的,=是发送到发放-*/+,./<>?';:","utf-8");
        System.out.println("加密后的:" + encode);
        String decode = URLDecoder.decode(encode,"utf-8");
        System.out.println("解密后的:"+decode);

    }
}

