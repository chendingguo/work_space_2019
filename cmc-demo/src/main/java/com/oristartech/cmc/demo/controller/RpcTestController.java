package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.config.annotation.Permission;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.cinema.api.DictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RpcTestController {

    @Reference
    DictService dictService;

    @GetMapping("/getDictInfo")
    public ResultModel getDictInfo(String code){
       return  dictService.getDictInfo(code);


    }


    /**
     * 资源请求方法
     * @param request
     * @return
     */
    @Permission(code="resource_code_1" ,name="资源名称1")
    @GetMapping(value = "/methodA")
    public ResultModel methodA( HttpServletRequest request) {
        return ResultModel.OK("");
    }

}
