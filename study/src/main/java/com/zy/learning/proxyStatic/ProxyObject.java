package com.zy.learning.proxyStatic;

public class ProxyObject extends AbstractObject {

    private  AbstractObject abstractObject;
    public ProxyObject(AbstractObject abstractObject){
        this.abstractObject = abstractObject;
    }

    @Override
    protected void operation() {
        System.out.println("do some thing  before .....");
        abstractObject.operation();
        System.out.println("do some thing  after .....");
    }
}
