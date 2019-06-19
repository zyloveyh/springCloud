package com.zy.learning.proxyStatic;

import org.junit.Test;

public class TestProxyStatic {
    @Test
    public  void testProxy(){
        ProxyObject proxyObject = new ProxyObject(new RealObject());
        proxyObject.operation();
    }
}
