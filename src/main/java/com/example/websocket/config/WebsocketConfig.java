package com.example.websocket.config;

import com.example.websocket.server.ChatEndpoint;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
@Configuration
public class WebsocketConfig {
   @Bean
    public ChatEndpoint chatEndpoint(){
    ProxyFactory factory = new ProxyFactory(new ChatEndpoint());

       return new ChatEndpoint();
    }
    // main one is ServerEndpointExporter which prevents Servlet container's scan for WebSocket
  @Bean
    public ServerEndpointExporter endpointExporter(){

        return new ServerEndpointExporter();
    }

}
