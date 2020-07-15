package com.nbank.dm.rule.server.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class Test {

    public static void main(String[] args) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf
                .createClient("http://localhost:8002/webservice/ruleEngine?wsdl");
        // getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
        Object[] objects = client.invoke("ruleEngine", "111","123","1234");
        System.out.println(objects[0]);
    }

}
