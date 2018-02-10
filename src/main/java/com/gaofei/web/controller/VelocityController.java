package com.gaofei.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaoQingming on 2017/9/6 0006.
 */
@Controller
@RequestMapping("velocity")
public class VelocityController {
    Logger logger = LoggerFactory.getLogger(VelocityController.class);

    @RequestMapping("index")
    public String testVelocity(ModelMap map) {
        map.addAttribute("test","testValue");
        List<String> list = new ArrayList<String>();
        list.add("String1");
        list.add("String2");
        list.add("String3");
        list.add("String4");
        List<String> list1 = null;
        String[] array = new String[2];
        array[0] = "array";
        map.put("array",array);
        map.put("list1",list1);
        map.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("normaltest")
    public String normaltest() {
        return "layouttest/normaltest";
    }

    @RequestMapping("specialtest")
    public String specialtest() {
        return "layouttest/specialtest";
    }

    //velocity各种函数测试
    //如果一个参数required = false ，连接中不写这个字段，赋值为null，如果写了字段，但是什么值都没传，认为是空字符串。
    @RequestMapping("variableFunction")
    public String variableFunction(@RequestParam(value = "emptyString",required = false)String emptyString,
                                   ModelMap map) {
        //map.addAttribute("emptyString",emptyString);
        logger.info("emptyString:{}",emptyString);
        return "/velocityTest/velocityFunction";
    }
}
