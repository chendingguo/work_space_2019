package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.customer.api.GlobalParamService;
import com.oristartech.cmc.customer.facade.api.MysqlDataInitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class MysqlDataTestController {


    @Reference
    MysqlDataInitService mysqlDataInitService;

    @Reference
    GlobalParamService globalParamService;


    @PostMapping(value = "/initCpmDict")
    public ResultModel initCpmDict( long consumerId) {

         mysqlDataInitService.initCpmDict(consumerId);
         return ResultModel.OK();
    }

    @PostMapping(value = "/initCpmSmsTemplate")
    public ResultModel initCpmSmsTemplate( long consumerId) {

        mysqlDataInitService.initCpmSmsTemplate(consumerId);
        return ResultModel.OK();
    }


}
