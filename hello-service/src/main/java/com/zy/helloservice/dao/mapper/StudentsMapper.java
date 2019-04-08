package com.zy.helloservice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.helloservice.dao.domain.Students;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentsMapper extends BaseMapper<Students> {
    int deleteByPrimaryKey(Integer id);

    int insert(Students record);

    Students selectByPrimaryKey(Integer id);

    List<Students> selectAll();

    int updateByPrimaryKey(Students record);
}