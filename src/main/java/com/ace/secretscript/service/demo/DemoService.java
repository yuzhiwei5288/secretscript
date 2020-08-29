package com.ace.secretscript.service.demo;

import com.ace.secretscript.entity.Demo;

import java.util.List;

public interface DemoService {

    List<Demo> getList();

    int insert(Demo demo);
}
