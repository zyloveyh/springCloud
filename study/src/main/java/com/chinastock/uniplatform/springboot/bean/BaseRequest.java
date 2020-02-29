//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chinastock.uniplatform.springboot.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;

public class BaseRequest<Param> {
    @JSONField(
            name = "PageInfo"
    )
    private BasePageInfo pageInfo;

    @JSONField(
            name = "Param"
    )
    @NotNull
    private Param param;

    public BaseRequest() {
    }

    public Param getParam() {
        return this.param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public BasePageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(BasePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
