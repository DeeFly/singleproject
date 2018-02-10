package com.gaofei.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GaoQingming on 2017/12/25 0025.
 */
public class SecondeExceptionHandler implements HandlerExceptionResolver, PriorityOrdered {
    Logger logger = LoggerFactory.getLogger(SecondeExceptionHandler.class);
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        return null;
    }

    public int getOrder() {
        return -2;
    }
}
