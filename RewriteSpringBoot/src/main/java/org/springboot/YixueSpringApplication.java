package org.springboot;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

public class YixueSpringApplication {
    public static void run(Class clazz) {
        //创建Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);//扫描clazz所在包，clazz是配置类，会扫描来取到带有@Component注解的类，创建Bean对象，放入Spring容器
        applicationContext.refresh();

        //启动webServer
        WebServer webServer = getWebServer(applicationContext);
        webServer.start(applicationContext);
    }

    private static WebServer getWebServer(WebApplicationContext applicationContext) {
        //容器里拿WebServer
        Map<String,WebServer> webServers = applicationContext.getBeansOfType(WebServer.class);
        if(webServers.size()==0) {
            throw new NullPointerException();
        }
        if(webServers.size()>1) {
            throw new IllegalStateException();
        }
        return webServers.values().stream().findFirst().get();
    }
}
