package com.ace.secretscript.mapper.ace;

import com.ace.secretscript.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {

    List<Demo> getList(Demo demo);

    int insert(Demo demo);
}
