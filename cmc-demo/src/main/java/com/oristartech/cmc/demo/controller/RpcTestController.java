package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.OpLog;
import com.oristartech.cmc.base.config.annotation.Permission;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.base.domain.UserModel;
import com.oristartech.cmc.base.open.service.UserAuthService;
import com.oristartech.cmc.base.open.service.UserCommonService;
import com.oristartech.cmc.base.open.service.UserOrgService;
import com.oristartech.cmc.cinema.api.DictService;
import com.oristartech.cmc.product.facade.api.MyJobFacade;
import com.oristartech.cmc.product.facade.domain.MyJob;
import com.oristartech.cmc.uat.api.RoleService;
import com.oristartech.cmc.uat.api.UserLoginService;
import com.oristartech.cmc.uat.api.UserService;
import com.oristartech.cmc.uat.dto.sys.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//import com.oristartech.cmc.cinema.api.DictService;


@RestController
@RequestMapping("/demo")
public class RpcTestController {

    @Reference(version = "1.0")
    DictService dictService;

    @Reference(version = "1.0")
    UserAuthService userAuthService;

    @Reference(version = "1.0")
    UserOrgService userOrgService;

    @Reference
    UserCommonService userCommonService;

    @Reference
    UserService userService;

    @Reference
    RoleService roleService;

    @Reference
    UserLoginService userLoginService;




    @GetMapping("/getDictInfo")
    public ResultModel getDictInfo(String code) {
        return dictService.getDictInfo(code);

    }


    @GetMapping("/testUri/{username}")
    public ResultModel testUri(@PathVariable String username) {

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

    @GetMapping("/getUserResourceInterceptInfo")
    public ResultModel getUserResourceInterceptInfo(String userUid, HttpServletRequest request) {
        return ResultModel.OK(userAuthService.getUserResourceInterceptInfo(userUid));

    }

    @GetMapping("/getUserCinemas/")
    public ResultModel getUserCinemas(String token, HttpServletRequest request) {
        String userUid = userAuthService.getUserUidByToken(token);
        return ResultModel.OK(userAuthService.getUserCinemas(userUid));

    }

    @OpLog(opName="testName",opDesc="testDesc")
    @PostMapping("/getUserResourcesByFunType")
    public ResultModel getUserResourcesByFunType(String userUid, String funType, HttpServletRequest request) {

        return ResultModel.OK(userAuthService.getUserResourcesByFunType(userUid, funType));
    }

    @GetMapping("/getUserBriefInfo")
    public ResultModel getUserBriefInfo(String userUid, HttpServletRequest request) {

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
    public ResultModel getConsumerUser(Long consumerId, HttpServletRequest request) {

        return ResultModel.OK(userCommonService.getUserByConsumerId(consumerId));
    }

    @GetMapping(value = "/getUserByOrgUid")
    public ResultModel getUserByOrgUid(Long consumerId, String orgUid, HttpServletRequest request) {
        List<String> roleList = new ArrayList<>();
        roleList.add("a934f0db0145492491fd6f474e0fc2d8");
        return ResultModel.OK(userCommonService.getUserByPage(1, 10, consumerId, 632517, "", ""));
    }


    @GetMapping(value = "/logout")
    public ResultModel logout(String token) {
        return userService.logout(token);
    }

    @GetMapping(value = "/modifyUserPassword")
    public ResultModel modifyUserPassword(String loginName, String password, String newPassword, Long customerId) {
        return userService.modifyPosUserPassword(loginName, password, newPassword, customerId);

    }


    @GetMapping(value = "/getRoleByConsumerId")
    public ResultModel getRoleByConsumerId(Long consumerId) {
        return ResultModel.OK(roleService.getRoleByConsumerId(consumerId));

    }


    @GetMapping("/getOrgTreeByConsumerId")
    public ResultModel getOrgTreeByConsumerId(long consumerId, HttpServletRequest request) {
        return ResultModel.OK(userOrgService.getOrgTreeByConsumerId(consumerId));

    }

    @GetMapping(value = "/getUserByPage")
    public ResultModel getUserByPage(Long consumerId, long cinemaId, HttpServletRequest request) {

        return ResultModel.OK(userCommonService.getUserByPage(2, 10, consumerId, cinemaId, "", ""));
    }


    @GetMapping(value = "/getOrgTreeAndCinemaByUser")
    public ResultModel getOrgTreeAndCinemaByUser(String userUid, HttpServletRequest request) {
        return ResultModel.OK(userOrgService.getOrgTreeAndCinemaByUser(userUid));
    }

    @GetMapping("/getUserByConsumerId")
    public ResultModel getUserByConsumerId(long consumerId, HttpServletRequest request) {
        return ResultModel.OK(userCommonService.getUserByConsumerId(consumerId));

    }

    @GetMapping("/getUserByRoleUid")
    public ResultModel getUserByRoleUid(long consumerId,String roleUid, HttpServletRequest request) {
        List <String> list=new ArrayList<>();
        list.add(roleUid);
        return ResultModel.OK(userCommonService.getUserByRoleUid(consumerId,list));

    }


    @GetMapping("/getUserAuthCinemas")
    public ResultModel getUserAuthCinemas(String userUid, HttpServletRequest request) {

        return ResultModel.OK(userService.getUserAuthCinemas(userUid));

    }

    @GetMapping("/getUsersByUidList")
    public ResultModel getUsersByUidList(String userUid, HttpServletRequest request) {
        List<String> list=new ArrayList<>();
        list.add(userUid);

        return ResultModel.OK(userService.getUsersByUidList(list));

    }

    @GetMapping("/getUserRolesByUserUid")
    @Permission(code = "ctm_*",name="name")
    public ResultModel getUserRolesByUserUid(String userUid, HttpServletRequest request) {

        return ResultModel.OK(userService.getUserRolesByUserUid(userUid));

    }

    @GetMapping("/getUsers")
    public ResultModel getUsers(HttpServletRequest request) {
        UserModel userModel=new UserModel();
        userModel.setConsumerId(805852L);
        userModel.setUserType((byte)9);
        return ResultModel.OK(userCommonService.getUsers(userModel));

    }

    @GetMapping("/getCinemaBrandsByConsumerId")
    public ResultModel getCinemaBrandsByConsumerId(long consumerId,HttpServletRequest request) {

        return ResultModel.OK(userOrgService.getCinemaBrandsByConsumerId(consumerId));

    }
    @GetMapping("/sendSmsVerifyCode")
    public ResultModel sendSmsVerifyCode(String consumerCode,String mobile,HttpServletRequest request) {

        return userLoginService.sendSmsVerifyCode(consumerCode,mobile,"");

    }

    @PostMapping("/loginByMobileVerifyCode")
    public ResultModel loginByMobileVerifyCode(String mobile,String verifyCode,HttpServletRequest request) {

        return userLoginService.loginByMobileVerifyCode("oristar",mobile,verifyCode);

    }

    @GetMapping("/queryDictPropertyList")
    public ResultModel queryDictPropertyList(String dictCode,String propertyCode, String propertyName, int status) {

        return  ResultModel.OK(dictService.queryDictPropertyList(dictCode,propertyCode,propertyName,status));

    }


    @GetMapping("/testPathVariable/{s}")
    public ResultModel testPathVariable(@PathVariable("s") String s) {
        return  ResultModel.OK(s);
    }


    @OpLog(opName="testName",opDesc="testDesc")
    @PostMapping("/saveLog")
    public ResultModel saveLog(@RequestBody User user) {

        return ResultModel.OK(user);
    }



}
