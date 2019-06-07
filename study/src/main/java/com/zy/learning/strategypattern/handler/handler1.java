package com.zy.learning.strategypattern.handler;

import com.zy.learning.entity.Woman;
import com.zy.learning.strategypattern.abstractclass.AbstractHandler;
import com.zy.learning.strategypattern.annotation.HandlerType;

@HandlerType("1")
public class handler1 extends AbstractHandler {
    @Override
    public String handle(Woman woman) {
        return null;
    }
}
