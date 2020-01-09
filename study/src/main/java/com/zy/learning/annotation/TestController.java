package com.zy.learning.annotation;

import javax.validation.Valid;

public class TestController {

    public void getName(@Valid ResponseBase<Son> responseBase, String age,@Valid Son son,@Valid Person person) {
        System.out.println(responseBase.getT());
    }

/*    public void getName(@Valid ResponseBase<Son> responseBase) {
        System.out.println(responseBase.getT());
    }*/
}
