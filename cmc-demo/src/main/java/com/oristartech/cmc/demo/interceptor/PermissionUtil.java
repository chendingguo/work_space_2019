package com.oristartech.cmc.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.oristartech.cmc.base.config.annotation.Permission;
import com.oristartech.cmc.base.constants.ErrorTypeEnum;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 过滤权限工具类
 */
public class PermissionUtil {

    public static final String CPM_USER_TOKEN = "Cpm-User-Token";


    public static boolean handlePermission(HttpServletRequest request, HttpServletResponse response,
                                            Object handler, UserAuthService uas)
            throws Exception {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //获取当前方法注解
        Permission permission = method.getAnnotation(Permission.class);
        if (permission == null) {
            //不需要校验权限
            return true;
        } else {

            String token = request.getHeader(PermissionUtil.CPM_USER_TOKEN);

            if (token == null) {
                int code = ErrorTypeEnum.USER_NOT_LOGIN.getCode();
                String msg = ErrorTypeEnum.USER_NOT_LOGIN.getMsg();
                ResultModel resultModel = new ResultModel(false, code, msg);

                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(resultModel));
                out.flush();
                out.close();

                 return false;

            }


            String userUid = uas.getUserUidByToken(token);

            //token失效
            if (userUid==null||userUid.equals("")) {
                int code = ErrorTypeEnum.USER_TOKEN_INVALID.getCode();
                String msg = ErrorTypeEnum.USER_TOKEN_INVALID.getMsg();
                ResultModel resultModel = new ResultModel(false, code, msg);
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(resultModel));
                return false;
            }
            //获取用户被授权的资源code列表
            List<String> codeList = uas.getUserResources(userUid);
            if (codeList.contains(permission.code())) {
                return true;
            } else {
                //没有资源权限
                int code = ErrorTypeEnum.RESOURCE_FORBIDDEN.getCode();
                String msg = ErrorTypeEnum.RESOURCE_FORBIDDEN.getMsg();
                msg+=":"+permission.code();
                ResultModel resultModel = new ResultModel(false, code, msg);
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(resultModel));
                out.flush();
                out.close();
                return false;
            }
        }


    }




}
