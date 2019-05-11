package com.oristartech.cmc.demo.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
import com.oristartech.cmc.base.util.ResourceInterceptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 权限认证拦截器
 */
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Reference(version = "1.0")
    UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

//        try {
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("application/json;charset=UTF-8");
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            String uri = request.getRequestURI();
//            String methodName = method.getName();
//
//            //从header中拿 token
//            String token = request.getHeader(ResourceInterceptUtil.CPM_USER_TOKEN);
//            ResultModel resultModel=ResourceInterceptUtil.handlePermission(userAuthService, token,uri, methodName);
//            if(resultModel.isResult()){
//                return true;
//            }else{
//                PrintWriter out = response.getWriter();
//                out.print(JSON.toJSONString(resultModel));
//                out.flush();
//                out.close();
//                return false;
//            }
//
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
        return true;
    }


}
