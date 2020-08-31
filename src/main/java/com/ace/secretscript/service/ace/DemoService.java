package com.ace.secretscript.service.ace;

import com.ace.secretscript.entity.Demo;

import java.util.List;

public interface DemoService {

    List<Demo> getList(Demo demo);

    int insert(Demo demo);
}
