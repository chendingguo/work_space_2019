package com.oristartech.cmc.demo.interceptor;

import com.oristartech.cmc.base.config.annotation.Permission;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 权限认证拦截器
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        if (permission == null) {
            return true;
        }
        if (permission.code().equals("no")) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("没有权限");
            out.flush();
            out.close();
            return false;
        }
        return true;

    }




}
