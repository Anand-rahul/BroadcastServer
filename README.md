# WebSocket Broadcast Server with Spring Boot and Spring Shell

## Overview
 Project Link - https://roadmap.sh/projects/broadcast-server

This project implements a WebSocket server using Spring Boot and Spring Shell. The server supports broadcasting messages to all connected clients and includes a Spring Shell CLI interface to monitor the server.

### Features
- **WebSocket Server**: Handles multiple client connections and broadcasts messages to all connected clients.
- **Spring Shell CLI**: Provides commands to start the server, connect clients, and view active users with their WebSocket endpoints.

### Shell Commands

- **broadcast-server stats**: Show the number of active users and their IPs
- **broadcast-server connect**: Connect as a client to the broadcast server
- **broadcast-server start**: Start the broadcast server

### PreRequisites
- Java 17
- Maven

### Setup

- Clone the Repo
  ```
  git clone https://github.com/Anand-rahul/BroadcastServer.git
  cd BroadcastServer
- Build the Project

  ```
  mvn clean install

- Dependencies (Add in properties File)

  ```
   server.shutdown=graceful
   spring.lifecycle.timeout-per-shutdown-phase=30s
   spring.shell.interactive.enabled=true
   spring.shell.script.enabled=true
- Run the application:

    ```
    mvn spring-boot:run
### Example

1. Sample Shell Command

![image](https://github.com/user-attachments/assets/f1253274-8625-4924-b472-de794f7b0e2b)

2. Create multiple clients using preferable software (Postman)

![image](https://github.com/user-attachments/assets/b46421e5-536f-402b-9e9f-a940e27080f7)
