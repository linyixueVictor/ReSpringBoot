package org.springboot;

import org.eclipse.jetty.util.Jetty;
import org.springframework.web.context.WebApplicationContext;

public class JettyWebServer implements WebServer {
    @Override
    public void start(WebApplicationContext applicationContext) {
        System.out.println("jetty启动");
    }
}
