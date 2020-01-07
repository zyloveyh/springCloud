package com.zy.learning.annotation;

import javax.validation.Valid;

public class TestController {

    public void getName(@Valid ResponseBase<Son> responseBase, String age) {
        System.out.println(responseBase.getT());
    }


}
