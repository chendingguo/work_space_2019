package com.oristartech.cmc.demo.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.open.service.UserAuthService;
import com.oristartech.cmc.base.util.PermissionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限认证拦截器
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Reference(version = "1.0")
    UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {

        try {
            return PermissionUtil.handlerPermission(request, response, handler, userAuthService);
        } catch (Exception e) {
           //TODO:
        }
        return true;


    }


}
