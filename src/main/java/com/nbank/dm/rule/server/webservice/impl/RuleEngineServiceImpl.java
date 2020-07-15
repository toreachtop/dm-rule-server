package com.nbank.dm.rule.server.webservice.impl;

import com.nbank.dm.rule.server.entity.ResultContent;
import com.nbank.dm.rule.server.webservice.RuleEngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

@Service
@WebService(serviceName = "RuleEngineService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.server.rule.dm.nbank.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.nbank.dm.rule.server.webservice.RuleEngineService" // 接口地址
)
@Slf4j
public class RuleEngineServiceImpl implements RuleEngineService {

    /**
     * 调用规则引擎
     *
     * @param projectCode   规则项目编号
     * @param enterPoint    规则调用编码
     * @param businessInput 规则业务参数
     */
    @Override
    public ResultContent ruleEngine(@WebParam(name = "projectCode") String projectCode,
                                    @WebParam(name = "enterPoint") String enterPoint,
                                    @WebParam(name = "businessInput") String businessInput) {
        try {
            String url = "http://localhost:18833/ruleService/ruleEngineService_vo";
            //入参报文
            String jsonString = "{\"businessInput\":{\"testDefault\":{\"age\":18,\"weight\":\"\"}},\"enterPoint\":\"test_default_union\",\"projectCode\":\"amiee\"}";
//            Map<String, String> requestMap = new HashMap<>();
//            requestMap.put("projectCode", projectCode);
//            requestMap.put("enterPoint", enterPoint);
//            requestMap.put("businessInput", businessInput);
//            String result = HttpUtils.sendPost(requestMap, url);
//            System.out.println(result);
//            return result;
            return ResultContent.buildSuccess(jsonString);
        } catch (Exception e) {
            log.error("调用规则引擎失败，msg={}", e.getMessage());
            return null;
        }
    }
}
