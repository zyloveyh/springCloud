package com.zy.learning.proxyCGLib;

import com.zy.learning.proxyDynamic.InterfaceObject;

public class RealObject implements InterfaceObject {

    @Override
    public void operation() {
        System.out.println("IM ralObject ......operation method");
    }
}
