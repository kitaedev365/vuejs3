package com.example.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.example.sns.util.ChatHandler;

@Configuration
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ChatHandler는 WebSocket 요청을 처리하는 핸들러 클래스
        registry.addHandler(new ChatHandler(), "/api/chat2")
        .setAllowedOrigins("http://localhost:5173") // 명시적인 출처 추가
        .withSockJS(); // SockJS 사용 (WebSocket을 지원하지 않는 브라우저에서도 사용 가능)
    }

    public WebSocketHandler chatHandler() {
        return new ChatHandler();
    }

    // SockJS 지원을 위한 Bean 등록 (옵션)
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}