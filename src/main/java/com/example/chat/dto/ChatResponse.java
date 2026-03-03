package com.example.chat.dto;

import java.time.LocalDateTime;

/**
 * Response DTO for chat endpoint.
 * Contains the AI's response along with metadata.
 */
public class ChatResponse {

    private String response;
    private String model;
    private LocalDateTime timestamp;
    private String systemPromptUsed;

    public ChatResponse() {
    }

    public ChatResponse(String response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }

    public ChatResponse(String response, String model) {
        this.response = response;
        this.model = model;
        this.timestamp = LocalDateTime.now();
    }

    public ChatResponse(String response, String model, String systemPromptUsed) {
        this.response = response;
        this.model = model;
        this.systemPromptUsed = systemPromptUsed;
        this.timestamp = LocalDateTime.now();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSystemPromptUsed() {
        return systemPromptUsed;
    }

    public void setSystemPromptUsed(String systemPromptUsed) {
        this.systemPromptUsed = systemPromptUsed;
    }
}

