package com.ruoyi.framework.config;


import com.ruoyi.framework.web.service.WebSocketServer;
import com.ruoyi.system.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author sett
 * @date 2023年03月26日 11:06
 * @title
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Autowired
    private void setIMessageService(IMessageService messageService){
        WebSocketServer.iMessageService = messageService;
    }

}
