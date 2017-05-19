package com.wittymonkey.interceptor;

import com.wittymonkey.entity.User;
import org.hibernate.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by neilw on 2017/5/19.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private List<String> except;

    public List<String> getExcept() {
        return except;
    }

    public void setExcept(List<String> except) {
        this.except = except;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("loginUser");
        if (user == null){
            String url = httpServletRequest.getRequestURI();
            if (!except.contains(url)) {
                httpServletResponse.sendRedirect("/toLogin.do");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
