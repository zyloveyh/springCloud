package com.zy.learning.proxyDynamic;

public class RealObject implements InterfaceObject {

    @Override
    public void operation() {
        System.out.println("IM ralObject ......operation method");
    }
}
