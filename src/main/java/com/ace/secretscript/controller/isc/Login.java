package com.ace.secretscript.controller.isc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("isc")
public class Login {

    @GetMapping("Login")
    public void getUserInfo(){

    }
}
