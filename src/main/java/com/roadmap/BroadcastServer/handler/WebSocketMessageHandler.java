package com.roadmap.BroadcastServer.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

@Component
public class WebSocketMessageHandler extends TextWebSocketHandler {

    private final Map<WebSocketSession, String> sessions = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Get client's IP address
        InetSocketAddress remoteAddress = session.getRemoteAddress();
        String clientIp = remoteAddress != null ? remoteAddress.getAddress().getHostAddress() : "Unknown IP";

        // Store the session and IP address
        sessions.put(session, clientIp);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        broadcastMessage(session.getRemoteAddress().getAddress().getHostAddress().concat(":").concat(message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    private void broadcastMessage(String message) throws IOException {
        for (WebSocketSession session : sessions.keySet()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
    public int getActiveUsersCount() {
        return sessions.size();
    }

    public Map<WebSocketSession, String> getActiveUsersWithIps() {
        return sessions;
    }
}