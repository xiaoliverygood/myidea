package com.example.interceptor;

import com.example.model.LoginAuth;
import com.example.utility.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginAuth loginAuth = (LoginAuth) UserHolder.get(request.getHeader("token"));
        if (loginAuth == null) {
             //未登录
             response.setContentType("application/json;charset=UTF-8");
             response.getWriter().write("{\"code\":401,\"message\":\"用户未登录\"}");
             return false;
         }
         if (loginAuth.getToken() == null) {
             response.setContentType("application/json;charset=UTF-8");
             response.getWriter().write("{\"code\":401,\"message\":\"登录过期\"}");
             return false;
             //登录已过期
         }
         if (!loginAuth.getToken().equals(request.getHeader("token"))) {
             response.setContentType("application/json;charset=UTF-8");
             response.getWriter().write("{\"code\":401,\"message\":\"//前端传入token与服务器保存的token不一致\"}");
             return false;
             //前端传入token与服务器保存的token不一致
         }
        return true;
    }
}
