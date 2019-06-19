package com.zy.learning.proxyStatic;

public class RealObject extends AbstractObject {
    @Override
    protected void operation() {
        System.out.println("IM ralObject ......operation method");
    }
}
