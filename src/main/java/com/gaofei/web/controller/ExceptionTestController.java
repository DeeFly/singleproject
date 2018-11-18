package com.gaofei.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by GaoQingming on 2017/12/25 0025.
 */
@Controller
@RequestMapping("exception")
public class ExceptionTestController {

    @RequestMapping("test")
    @ResponseBody
    public String test(@RequestParam("param")String param) {
        if ("yes".equals(param)) {
            throw new RuntimeException("测试");
        }
        return "no exception";
    }
}
