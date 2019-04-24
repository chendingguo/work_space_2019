package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.config.annotation.Permission;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
//import com.oristartech.cmc.cinema.api.DictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/demo")
public class RpcTestController {

//    @Reference(version = "1.0")
//    DictService dictService;

    @Reference(version = "1.0")
    UserAuthService userAuthService;

//    @GetMapping("/getDictInfo")
//    public ResultModel getDictInfo(String code) {
//        return dictService.getDictInfo(code);
//
//    }

    @GetMapping("/getUserUidByToken")
    public ResultModel getUserUidByToken(String token, HttpServletRequest request) {
        return ResultModel.OK(userAuthService.getUserUidByToken(token));

    }

    @GetMapping("/getUserResources")
    public ResultModel getUserResources(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserResources(userUid));

    }

    @GetMapping("/getUserCinemas")
    public ResultModel getUserCinemas(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserCinemas(userUid));

    }

    @GetMapping("/")
    public ResultModel getUserResourcesByFunType(String token, String funType,HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserResourcesByFunType(userUid,funType));
    }

    @GetMapping("/getUserBriefInfo")
    public ResultModel getUserBriefInfo(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserBriefInfo(userUid));

    }


    /**
     * 资源请求方法
     *
     * @param request
     * @return
     */

    @GetMapping(value = "/getBusinessInfo")
    public ResultModel getBusinessInfo(HttpServletRequest request) {

        return ResultModel.OK("这是业务测试数据");
    }

}
