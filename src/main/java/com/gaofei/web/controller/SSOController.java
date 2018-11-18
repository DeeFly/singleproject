package com.gaofei.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by GaoQingming on 2017/11/14 0014.
 */
@Controller
@RequestMapping("SSOServer")
public class SSOController {
    //保存有多少个系统用这个token登陆了，在注销的时候将这几个系统中的session清除，并将server本地的全局session清除
    //或者本地session过期了，根据这个map将登陆过的子系统中的session清除。
    private static final Map<String,Set<String>> sessionMap = new HashMap();
    Logger logger = LoggerFactory.getLogger(SSOController.class);

    @RequestMapping("authentication")
    public ModelAndView testAuthentication(@RequestParam("from")String from, ModelMap map,HttpSession session) {
        logger.info(from);
        if (Objects.equals(session.getAttribute("isLogin"),"yes")) {
            String token = (String)session.getAttribute("token");
            return new ModelAndView("redirect:" + from + "?token=" + token);
        }
        map.put("from",from);
        return new ModelAndView("login/login");
    }

    @RequestMapping("dologin")
    public ModelAndView testDoLogin(@RequestParam("username")String userName,
                              @RequestParam("password")String password,
                              @RequestParam("from")String from,
                              HttpServletRequest request) {
        logger.info("from:{}",from);
        if ("gaofei".equals(userName) && "pass".equals(password)) {
            String token = UUID.randomUUID().toString();
            request.getSession().setAttribute("token",token);
            request.getSession().setAttribute("isLogin","yes");
            Set<String> webs = sessionMap.get(token) == null ? new HashSet<String>() : sessionMap.get(token);
            webs.add(from);
            sessionMap.put(token,webs);
            return new ModelAndView("redirect:" + "http://www.gaofei.com/dubboweb/controller/handlerTest?token=" + token);
        }
        return new ModelAndView("forward:" + "authentication");
    }

    @RequestMapping("checkToken")
    @ResponseBody
    public String checkToken(@RequestParam("token")String token,
                             @RequestParam("from")String from) {
        Set webs = sessionMap.get(token);
        if (webs != null) {
            webs.add(from);
            return "yes";
        }
        return "no";
    }
}
