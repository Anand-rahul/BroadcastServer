package com.roadmap.BroadcastServer.shell;

import com.roadmap.BroadcastServer.config.WebSocketMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@ShellComponent
public class BroadcastShellCommand {
    @Autowired
    private WebSocketMessageHandler webSocketMessageHandler;
    @ShellMethod(key="broadcast-server start",value="Start the broadcast server")
    public String startServer() {
        // The server is automatically started when the Spring Boot application runs
        return "Broadcast server started on ws://localhost:8080/broadcast";
    }

    @ShellMethod(key="broadcast-server connect",value="Connect as a client to the broadcast server")
    public String connectClient() {
        return "Client can connect to ws://localhost:8080/broadcast using a WebSocket client.";
    }

    @ShellMethod(key="broadcast-server stats",value="Show the number of active users and their IPs")
    public String showActiveUsers() {
        Map<WebSocketSession, String> activeUsers = webSocketMessageHandler.getActiveUsersWithIps();
        int activeUsersCount = webSocketMessageHandler.getActiveUsersCount();

        StringBuilder result = new StringBuilder("Active Users: " + activeUsersCount + "\n");
        activeUsers.forEach((session, ip) -> result.append("User: ").append(session.getId()).append(", IP: ").append(ip).append("\n"));

        return result.toString();
    }
}
