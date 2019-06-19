package com.zy.learning.proxyCGLib;

import org.junit.Test;

public class TestCGlib {
    @Test
    public  void testCGLib(){
        RealObject instance = (RealObject) new ProxyObject().getInstance(RealObject.class);
        instance.operation();

    }
}
