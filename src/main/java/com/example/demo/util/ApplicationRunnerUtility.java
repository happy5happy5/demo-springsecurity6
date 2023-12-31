package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.server.WebServer;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
class ApplicationRunnerUtility implements org.springframework.boot.ApplicationRunner {

    private final WebServerApplicationContext context;

    @Autowired
    public ApplicationRunnerUtility(WebServerApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(ApplicationArguments args) {
        WebServer webServer = context.getWebServer();
        int port = webServer.getPort();
        String hostName = getHostName();

        log.info("http://{}:{}", hostName, port);
        log.info("http://127.0.0.1:{}", port);
    }

    private String getHostName() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("error while getting host name", e);
            return "localhost";
        }
    }
}
