package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for Spring AI Chat.
 * This application demonstrates how to use Spring AI with OpenAI
 * and a custom system prompt loaded from a .st (StringTemplate) file.
 */
@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }
}

