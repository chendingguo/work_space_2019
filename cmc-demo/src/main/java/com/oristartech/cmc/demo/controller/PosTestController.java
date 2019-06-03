package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
import com.oristartech.cmc.base.open.service.UserCommonService;
import com.oristartech.cmc.base.open.service.UserOrgService;
import com.oristartech.cmc.uat.api.UserLoginService;
import com.oristartech.cmc.uat.api.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//import com.oristartech.cmc.cinema.api.DictService;


@RestController
@RequestMapping("/demo")
public class PosTestController {

//    @Reference(version = "1.0")
//    DictService dictService;

    @Reference(version = "1.0")
    UserAuthService userAuthService;

    @Reference(version = "1.0")
    UserLoginService userLoginService;


    @Reference(version = "1.0")
    UserOrgService userOrgService;

    @Reference
    UserCommonService userCommonService;

    @Reference
    UserService userService;




    @GetMapping(value = "/posLogin")
    public ResultModel logout(Long consumerId, Long cinemaId, String loginName, String password){
        return  userLoginService.posLogin(consumerId,cinemaId,loginName,password);
    }

    @GetMapping(value = "/checkPosUserAuth")
    public ResultModel checkPosUserAuth(Long consumerId, Long cinemaId, String loginName, String password){
        return userService.checkPosUserAuth(consumerId,cinemaId,loginName,password);
    }

    @GetMapping(value = "/getPosAuthMenuTree")
    public ResultModel getPosAuthMenuTree(String userUid,long cinemaId){
        return userService.getPosAuthMenuTree(userUid,cinemaId);
    }



}
