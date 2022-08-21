package com.roy.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到请求:{}",request.getRequestURI());
        if(request.getSession().getAttribute("employee")!=null){
            BaseContext.setCurrentId((Long) request.getSession().getAttribute("employee"));
            return true;
        }

        if(request.getSession().getAttribute("user")!=null){
            BaseContext.setCurrentId((Long) request.getSession().getAttribute("user"));
            return true;
        }
        response.getWriter().write(JSON.toJSONString(new R(false,"NOTLOGIN")));

        return false;
//        return ;
    }
}
