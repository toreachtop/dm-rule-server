package com.nbank.dm.rule.server.config;

import com.nbank.dm.rule.server.webservice.HelloService;
import com.nbank.dm.rule.server.webservice.RuleEngineService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 *
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private RuleEngineService ruleEngineService;

    @Autowired
    private HelloService helloService;

    /**
     * 注入servlet  bean name不能dispatcherServlet 否则会覆盖dispatcherServlet
     *
     * @return ServletRegistrationBean
     */
    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 注册RuleEngineService接口到webservice服务
     *
     * @return endpoint
     */
    @Bean(name = "RuleEngineServiceEndpoint")
    public Endpoint sweptPayEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), ruleEngineService);
        endpoint.publish("/ruleEngine");
        return endpoint;
    }

    @Bean(name = "SayHelloServiceEndpoint")
    public Endpoint sayPayEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), helloService);
        endpoint.publish("/hello");
        return endpoint;
    }
}
