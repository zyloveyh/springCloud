package com.zy.learning.annotation.demotest.controller;

import com.chinastock.uniplatform.db.constant.CommonConstant;
import com.chinastock.uniplatform.springboot.bean.BasePageInfo;
import com.chinastock.uniplatform.springboot.bean.BaseRequest;
import com.chinastock.uniplatform.springboot.bean.BaseResponse;
import com.chinastock.uniplatform.springboot.bean.Meta;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zy.learning.annotation.Age;
import com.zy.learning.annotation.Name;
import com.zy.learning.annotation.demotest.dao.TEmployeeMapper;
import com.zy.learning.annotation.demotest.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "TEmployeeController", tags = "TEmployeeController服务接口")
@RequestMapping(value = CommonConstant.API_VERSION_1_0 + "/TEmployeeController")
@RestController
public class TEmployeeController {

    @Autowired
    TEmployeeMapper tEmployeeMapper;

    @ApiOperation(value = "根据主键ID删除 deleteByPrimaryKey")
    @RequestMapping(method = RequestMethod.DELETE, value = "/testParam")
    public Base<List<Map<String,Integer>>,String>  testParam(@Valid BaseRequest<TEmployee> tba) {
        return null;
    }


/*    public TEmployee testReturn(@Valid Base<TEmployee,String> tba) {
        return null;
    }

    private void getS() {

    }*/
   /* @ApiOperation(value = "查询 selectAll")
    @RequestMapping(headers = "Content-Type=application/json;charset=UTF-8", method = RequestMethod.POST, value = "/selectAll")
    public BaseResponse<List<TEmployee>> selectAll(@RequestBody BaseRequest<Object> request) {
        BasePageInfo pageInfo = request.getPageInfo();
        Page page = null;
        if (pageInfo != null) {
            page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), true);
            String orderBy = pageInfo.getOrderBy();
            if (null != orderBy && !orderBy.isEmpty()) {
                PageHelper.orderBy(orderBy);
            }
        }
        List<TEmployee> res = tEmployeeMapper.selectAll();
        BaseResponse<List<TEmployee>> resultResponse = new BaseResponse<List<TEmployee>>();
        resultResponse.setData(res);
        Meta meta = new Meta();
        meta.setCode("0");
        meta.setMessage("success");
        meta.setSuccess(true);
        resultResponse.setMeta(meta);
        if (page != null) {
            pageInfo.setTotal(page.getTotal());
            resultResponse.setPageInfo(pageInfo);
        }
        return resultResponse;

    }*/


/*	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteByPrimaryKey/{empNo}")




	public BaseResponse<Integer> deleteByPrimaryKey(@PathVariable String empNo, @Valid TEmployee te) {
		Integer res = tEmployeeMapper.deleteByPrimaryKey(empNo);
		BaseResponse<Integer> resultResponse = new BaseResponse<Integer>();
		resultResponse.setData(res);
		Meta meta = new Meta();
		meta.setCode("0");
		meta.setMessage("success");
		meta.setSuccess(true);
		resultResponse.setMeta(meta);
		return resultResponse;

	}*/

}