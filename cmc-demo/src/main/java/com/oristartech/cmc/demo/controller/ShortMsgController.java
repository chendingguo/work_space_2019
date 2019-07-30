package com.oristartech.cmc.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oristartech.cmc.base.domain.ResultModel;
import com.oristartech.cmc.product.facade.api.CpmSMSConfigService;
import com.oristartech.cmc.product.facade.api.ShortMsgService;
import com.oristartech.cmc.product.facade.domain.SmsRecord;
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

    @Reference
    CpmSMSConfigService cpmSMSConfigService;


    @PostMapping("/sendSingleMsg")
    public ResultModel sendSingleMsg(@RequestBody MsgRequest msgRequest, HttpServletRequest request) {
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
    public ResultModel sendSameContentBatchMsg(HttpServletRequest request) {
        MsgRequest msgRequest = new MsgRequest();

        for(int j=101;j<=1000;j++) {
            List<String> mobiles = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                mobiles.add(j+String.valueOf(i));

            }
            msgRequest.setSceneCode("CMC_002");
            msgRequest.setBatchNumber("batch_"+j);
            msgRequest.setBatchTitle("群发消息");
            msgRequest.setSender("test");
            msgRequest.setConsumerId(432814L);
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("msg", "content");
            msgRequest.setParamMap(paramMap);
            cpmSMSConfigService.createSmsRecordBatch(String.join(",", mobiles),"content",msgRequest);

        }

        return  null;

    }

    @GetMapping("/querySmsRecordTest")
    public ResultModel querySmsRecordTest(String sql) {
        return cpmSMSConfigService.querySmsRecordTest(sql);
    }
}
