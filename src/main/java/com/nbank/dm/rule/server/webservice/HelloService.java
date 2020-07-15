package com.nbank.dm.rule.server.webservice;

import com.nbank.dm.rule.server.entity.ResultContent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "HelloService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.server.rule.dm.nbank.com" // 与接口中的命名空间一致,一般是接口的包名倒
)
public interface HelloService {

    /**
     * 测试
     *
     * @param name 测试
     * @return String
     */
    @WebMethod
    ResultContent say(@WebParam(name = "name") String name);

}
