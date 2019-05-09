package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.product.facade.api.ShortMsgService;
import com.oristartech.cmc.product.facade.model.MsgRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController("/msg/")

public class ShortMsgController {

    @Reference
    ShortMsgService shortMsgService;


    @PostMapping
    public ResultModel sendSingleMsg(@RequestBody MsgRequest msgRequest, HttpServletRequest request){
        msgRequest.setTemplateCode("buy_card_template");
        msgRequest.setSender("test");
        msgRequest.setMobile("15313953922");
        Map<String, String> paramMap =new HashMap<>();
        paramMap.put("CardCode","88888");
        paramMap.put("Money","260");
        msgRequest.setParamMap(paramMap);

        return shortMsgService.sendSingleMsg(msgRequest);

    }
}
