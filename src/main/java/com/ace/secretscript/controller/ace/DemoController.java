package com.ace.secretscript.controller.ace;

import com.ace.secretscript.common.service.redis.RedisService;
import com.ace.secretscript.common.util.LoggerUtil;
import com.ace.secretscript.entity.Demo;
import com.ace.secretscript.service.ace.DemoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: ZW
 * @date 2020/8/29
 * @return
 **/
@RestController
@RequestMapping("iace")
public class DemoController {

    private static final String REQUEST_FLAG = "1";

    @Autowired
    private DemoService demoService;

    @Autowired
    private RedisService redisService;

    @GetMapping("demo")
    public String getDemo(Demo demo) {
        String jsonDemo = JSON.toJSONString(demo);
        redisService.set("jsonDemo",jsonDemo);
        String stringDemo = demo.toString();
        System.out.println(stringDemo);
        System.out.println(jsonDemo);
        List<Demo> list = this.demoService.getList(demo);
        redisService.set("demoList",JSON.toJSONString(list));
        return list.toString();
    }

    @PostMapping("demo")
    public String post(Demo demo) {
        int i = this.demoService.insert(demo);
        if (i == 0) {
            return "添加失败";
        }
        return "添加成功";
    }

    @GetMapping("jump")
    public void jumpToCenter(HttpServletRequest request, HttpServletResponse reponse) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String flag = request.getParameter("flag");

        try {
            if (REQUEST_FLAG.equals(flag)) {
                reponse.sendRedirect("http://localhost:6789/#/login?username=" + username + "&password=" + password);
            } else {
                reponse.sendRedirect("http://localhost:6789/#/login?username=" + username);
            }
        } catch (Exception e) {
            LoggerUtil.error("gg--炸了", e);
//            e.printStackTrace();
        }
    }

}
