package com.nbank.dm.rule.server.webservice;

import com.nbank.dm.rule.server.entity.ResultContent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 规则引擎相关接口
 *
 * @author jd
 */
@WebService(name = "RuleEngineService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.server.rule.dm.nbank.com" // 与接口中的命名空间一致,一般是接口的包名倒
)
public interface RuleEngineService {

    /**
     * 调用规则引擎
     *
     * @param projectCode   规则项目编号
     * @param enterPoint    规则调用编码
     * @param businessInput 规则业务参数
     * @return String
     */
    @WebMethod
    ResultContent ruleEngine(@WebParam(name = "projectCode") String projectCode,
                             @WebParam(name = "enterPoint") String enterPoint,
                             @WebParam(name = "businessInput") String businessInput);

}
