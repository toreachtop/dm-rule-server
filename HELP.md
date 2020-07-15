# Getting Started

### 客户端cxf-codegen-plugin使用
1、添加依赖
```
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
            <version>3.3.7</version>
        </dependency>
```
2、插件
```
                        <plugin>
                            <groupId>org.apache.cxf</groupId>
                            <artifactId>cxf-codegen-plugin</artifactId>
                            <version>3.3.7</version>
                            <executions>
                                <execution>
                                    <id>generate-sources</id>
                                    <phase>generate-sources</phase>
                                    <configuration>
                                        <!--生成路径代码-->
                                        <sourceRoot>${project.build.directory}/generated/cxf</sourceRoot>
                                        <wsdlOptions>
                                            <wsdlOption>
                                                <!--根据wsdl地址生成-->
                                                <wsdl>http://localhost:8002/webservice/hello?wsdl</wsdl>
                                                <!--根据本地wsdl文件生成：指定wsdl本地路径-->
                                                <!--<wsdl>XXX/use.wsdl</wsdl>-->
                                                <!--wsdlLocation配置生成-->
                                                <wsdlLocation>http://localhost:8002/webservice/hello?wsdl</wsdlLocation>
                                                <!--生成代码的包路径，如果不配置默认以当前包路径为准-->
                                                <extraargs>
                                                    <extraarg>-p</extraarg>
                                                    <!--生成代码的路径-->
                                                    <extraarg>com.demo</extraarg>
                                                </extraargs>
                                            </wsdlOption>
                                        </wsdlOptions>
                                    </configuration>
                                    <goals>
                                        <!--idea客户端会报红色提示，可忽略不用管-->
                                        <goal>wsdl2java</goal>
                                    </goals>
                                </execution>
                            </executions>
                        </plugin>
```
3、执行
        进入项目目录下执行
```
mvn generate-sources
```

### 发布webservice服务
1、添加依赖
```
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
            <version>3.3.7</version>
        </dependency>
```
2、编写服务
（1）接口定义
```
@WebService(
        // 与接口中指定的name一致
        name = "HelloService", 
        // 与接口中的命名空间一致,一般是接口的包名倒
        targetNamespace = "http://webservice.server.rule.dm.nbank.com" 
)
public interface HelloService {
    @WebMethod
    ResultContent say(@WebParam(name = "name") String name);
}
```
（2）实现接口
```
    @Service
    @WebService(
            // 与接口中指定的name一致
            serviceName = "HelloService", 
            // 与接口中的命名空间一致,一般是接口的包名倒
           targetNamespace = "http://webservice.server.rule.dm.nbank.com", 
            // 接口地址
           endpointInterface = "com.nbank.dm.rule.server.webservice.HelloService" 
   )
   @Slf4j
   public class HelloServiceImpl implements HelloService {
       @Override
       public ResultContent say(@WebParam(name = "name") String name) {
           return ResultContent.buildSuccess("Hello " + name);
       }
   }
```
3、接口发布
```
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
        //url请求前缀配置
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //发布服务ruleEngineService，定义不同的bean------sweptPayEndpoint
    @Bean(name = "RuleEngineServiceEndpoint")
    public Endpoint sweptPayEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), ruleEngineService);
        endpoint.publish("/ruleEngine");
        return endpoint;
    }

    //发布服务helloService：，定义不同的bean-------sayPayEndpoint
    @Bean(name = "SayHelloServiceEndpoint")
    public Endpoint sayPayEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), helloService);
        endpoint.publish("/hello");
        return endpoint;
    }
}
```
4、调用服务
```
    public static void main(String[] args) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf
                .createClient("http://localhost:8002/webservice/ruleEngine?wsdl");
        // getUser 为接口中定义的方法名称 张三为传递的参数 返回一个Object数组
        Object[] objects = client.invoke("ruleEngine", "111","123","1234");
        System.out.println(objects[0]);
    }
```