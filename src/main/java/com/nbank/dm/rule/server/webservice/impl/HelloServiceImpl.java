package com.nbank.dm.rule.server.webservice.impl;

import com.nbank.dm.rule.server.entity.ResultContent;
import com.nbank.dm.rule.server.webservice.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

@Service
@WebService(serviceName = "HelloService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.server.rule.dm.nbank.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.nbank.dm.rule.server.webservice.HelloService" // 接口地址
)
@Slf4j
public class HelloServiceImpl implements HelloService {


    /**
     * 测试
     *
     * @param name 测试
     * @return String
     */
    @Override
    public ResultContent say(@WebParam(name = "name") String name) {
        return ResultContent.buildSuccess("Hello " + name);
    }
}
