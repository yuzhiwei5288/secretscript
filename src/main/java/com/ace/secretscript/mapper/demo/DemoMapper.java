package com.ace.secretscript.mapper.demo;

import com.ace.secretscript.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {

    List<Demo> getList();

    int insert(Demo demo);
}
