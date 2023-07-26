package com.ivo.mas.system.Interceptor;
import com.ivo.mas.pojo.User;
import com.ivo.mas.service.MainService;
import com.ivo.mas.system.utils.SessionUtils;
import com.ivo.mas.system.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{

    public MainService mainService;

    public LoginInterceptor(MainService mainService){
        this.mainService = mainService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        User user = (User)request.getSession().getAttribute("user");
        return user !=null ? true:false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {


    }
}
