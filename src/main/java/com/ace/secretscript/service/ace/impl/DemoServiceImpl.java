package com.ace.secretscript.service.ace.impl;

import com.ace.secretscript.entity.Demo;
import com.ace.secretscript.mapper.ace.DemoMapper;
import com.ace.secretscript.service.ace.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> getList(Demo demo) {
        List<Demo> list = this.demoMapper.getList(demo);
        return list;
    }

    @Override
    public int insert(Demo demo) {
        int i = this.demoMapper.insert(demo);
        return i;
    }
}
