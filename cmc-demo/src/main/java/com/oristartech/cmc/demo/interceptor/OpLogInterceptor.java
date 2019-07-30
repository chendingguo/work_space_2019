package com.oristartech.cmc.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.oristartech.cmc.OpLog;
import com.oristartech.cmc.demo.util.HttpHelper;
import com.oristartech.cmc.uat.dto.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName: OpLogInterceptor
 * @Description 操作日志拦截器
 * @Author LKG
 * @date 2019/3/21
 * @Version 1.0
 */
@Slf4j
public class OpLogInterceptor extends HandlerInterceptorAdapter {





    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            try {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                String resourceCode=request.getParameter("resourceCode");
                String description=request.getParameter("description");
                OpLog opLog = ((HandlerMethod) handler).getMethodAnnotation(OpLog.class);
                if (opLog == null) {
                    return;
                }

                Map<String, String[]> paramMap= request.getParameterMap();
                String body=HttpHelper.getBodyString(request);

                //操作日志记录
                OperationLog operationLog = new OperationLog();
                operationLog.setOpName(resourceCode);
                operationLog.setOpDesc(description);

                System.out.println(JSON.toJSONString(paramMap));
            } catch (Exception e) {
                log.error("写入日志异常", e);
            }
        }
    }
}
