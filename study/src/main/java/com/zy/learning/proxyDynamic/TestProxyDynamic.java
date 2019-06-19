package com.zy.learning.proxyDynamic;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxyDynamic {
    @Test
    public  void testDynamic() throws IOException {
        RealObject realObject = new RealObject();

        InterfaceObject proxyInstance = (InterfaceObject) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(),
                new InvocationHandler() {
                    private void before(){
                        System.out.println("before... ...");
                    }
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        before();
                        if (method.getName().equalsIgnoreCase("operation")){
                            System.out.println("方法开始前......");
                            System.out.println(method.getName());
                            method.invoke(realObject,args);
                        }
                           return proxy;
                    }
                }
        );
        System.out.println(proxyInstance.getClass());
        proxyInstance.operation();
        byte[] $Proxy4s = ProxyGenerator.generateProxyClass("$Proxy4", new Class[]{proxyInstance.getClass()});
        FileOutputStream file = new FileOutputStream("D:\\ZY\\java\\project\\springCloud\\study\\src\\main\\java\\com\\zy\\learning\\proxyDynamic\\$Proxy4.class");
        file.write($Proxy4s);
        byte[] Proxy4s = ProxyGenerator.generateProxyClass("Proxy4", new Class[]{proxyInstance.getClass()});
        FileOutputStream file4 = new FileOutputStream("D:\\ZY\\java\\project\\springCloud\\study\\src\\main\\java\\com\\zy\\learning\\proxyDynamic\\Proxy4.class");
        file4.write(Proxy4s);
        file4.close();

    }
}
