package com.zy.helloservice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.helloservice.dao.domain.Students;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StudentsMapper extends BaseMapper<Students> {

}