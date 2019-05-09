package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.config.annotation.Permission;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
//import com.oristartech.cmc.cinema.api.DictService;
import com.oristartech.cmc.base.open.service.UserCommonService;
import com.oristartech.cmc.base.open.service.UserOrgService;
import com.oristartech.cmc.uat.api.UserLoginService;
import com.oristartech.cmc.uat.api.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/demo")
public class RpcTestController {

//    @Reference(version = "1.0")
//    DictService dictService;

    @Reference(version = "1.0")
    UserAuthService userAuthService;

    @Reference(version = "1.0")
    UserOrgService userOrgService;

    @Reference
    UserCommonService userCommonService;

    @Reference
    UserService userService;

//    @GetMapping("/getDictInfo")
//    public ResultModel getDictInfo(String code) {
//        return dictService.getDictInfo(code);
//
//    }


    @GetMapping("/testUri/{username}")
    public ResultModel testUri(@PathVariable String username){

        return ResultModel.OK(username);

    }
    @GetMapping("/getUserOrgCinemaTree")
    public ResultModel getUserOrgCinemaTree(String userUid, HttpServletRequest request) {
        return ResultModel.OK(userOrgService.getUserOrgCinemaTree(userUid));

    }

    @GetMapping("/getUserUidByToken")
    public ResultModel getUserUidByToken(String token, HttpServletRequest request) {
        return ResultModel.OK(userAuthService.getUserUidByToken(token));

    }

    @GetMapping("/getUserResources")
    public ResultModel getUserResources(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserResourceInterceptInfo(userUid));

    }

    @GetMapping("/getUserCinemas/")
    public ResultModel getUserCinemas(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserCinemas(userUid));

    }

    @GetMapping("/getUserResourcesByFunType")
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


    @GetMapping(value = "/getConsumerUser")
    public ResultModel getConsumerUser(Long consumerId,HttpServletRequest request) {

        return ResultModel.OK(userCommonService.getUserByConsumerId(consumerId));
    }

    @GetMapping(value = "/getUserByOrgUid")
    public ResultModel getUserByOrgUid(Long consumerId,String orgUid,HttpServletRequest request) {
       List<String> roleList=new ArrayList<>();
        roleList.add("a934f0db0145492491fd6f474e0fc2d8");
        return ResultModel.OK(userCommonService.getUserByPage(1,10,consumerId,632517,"",""));
    }


    @GetMapping(value = "/logout")
    public ResultModel logout(String token){
       return userService.logout(token);
    }
    @GetMapping(value="modifyUserPassword")
    public ResultModel modifyUserPassword(String loginName,String password,String newPassword,Long customerId){
        return userService.modifyPosUserPassword(loginName,password,newPassword,customerId);

    }


    @GetMapping(value = "/posLogin")
    public ResultModel logout(Long consumerId, Long cinemaId, String loginName, String password){
        return  ResultModel.OK(userService.posLogin(consumerId,cinemaId,loginName,password));
    }


}
