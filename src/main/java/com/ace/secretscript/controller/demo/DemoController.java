package com.ace.secretscript.controller.demo;

import com.ace.secretscript.entity.Demo;
import com.ace.secretscript.service.demo.DemoService;
import com.ace.secretscript.util.LoggerUtil;
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

    @GetMapping("demo")
    public String getDemo(Demo demo) {
        String sDemo = demo.toString();
        System.out.println(sDemo);
        List<Demo> list = this.demoService.getList();
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
            LoggerUtil.error("gg---", e);
//            e.printStackTrace();
        }
    }

}
