package com.demo.server.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @ApiOperation(value = "测试用controller1")
    @GetMapping("/personal/emp/testUrl1")
    public String testUrl1(){
        return "/personal/emp/testUrl1";
    }

    @ApiOperation(value = "测试用controller2")
    @GetMapping("/personal/remove/testUrl2")
    public String testUrl2(){
        return "/personal/remove/testUrl2";
    }
}
