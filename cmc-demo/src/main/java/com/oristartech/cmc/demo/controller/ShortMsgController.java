package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.product.facade.api.ShortMsgService;
import com.oristartech.cmc.product.facade.model.MsgRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class ShortMsgController {

    @Reference
    ShortMsgService shortMsgService;


    @PostMapping("/sendSingleMsg")
    public ResultModel sendSingleMsg(@RequestBody MsgRequest msgRequest, HttpServletRequest request){
//        msgRequest.setTemplateCode("buy_card_template");
//        msgRequest.setSender("test");
//        msgRequest.setMobile("15313953922");
//        Map<String, String> paramMap =new HashMap<>();
//        paramMap.put("CardCode","88888");
//        paramMap.put("Money","260");
//        msgRequest.setParamMap(paramMap);

        return shortMsgService.sendSingleMsg(msgRequest);

    }


    @GetMapping("/sendSameContentBatchMsg")
    public ResultModel sendSameContentBatchMsg( HttpServletRequest request){
        MsgRequest msgRequest=new MsgRequest();
        List<String> mobiles=new ArrayList<>();
        mobiles.add("15313953922");
        mobiles.add("13110430179");
        msgRequest.setSceneCode("HY_014");
        msgRequest.setSender("test");
        msgRequest.setConsumerId(805852);
        Map<String, String> paramMap =new HashMap<>();
        paramMap.put("msg","20190519");
        paramMap.put("Money","519");
        msgRequest.setParamMap(paramMap);

        return shortMsgService.sendSameContentBatchMsg(mobiles,msgRequest);

    }
}
