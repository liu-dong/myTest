package com.dong;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 使用spring boot+WebSocket 实现定时消息推送（基于注解）
 * <p>
 * 与主体项目无关
 */
@Configuration
public class WebSocketConfig {

    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
