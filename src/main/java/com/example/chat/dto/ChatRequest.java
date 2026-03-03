package com.example.chat.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * Request DTO for chat endpoint.
 * Contains the user's message and optional parameters for the chat.
 */
public class ChatRequest {

    @NotBlank(message = "Message cannot be empty")
    private String message;
    
    private Boolean enableSystemPrompt = true;
    
    private Map<String, Object> options;

    public ChatRequest() {
    }

    public ChatRequest(String message) {
        this.message = message;
    }

    public ChatRequest(String message, Boolean enableSystemPrompt) {
        this.message = message;
        this.enableSystemPrompt = enableSystemPrompt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getEnableSystemPrompt() {
        return enableSystemPrompt;
    }

    public void setEnableSystemPrompt(Boolean enableSystemPrompt) {
        this.enableSystemPrompt = enableSystemPrompt;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}

