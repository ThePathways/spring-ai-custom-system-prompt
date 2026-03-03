package com.example.chat.service;

import com.example.chat.client.ChatClientWrapper;
import com.example.chat.config.SystemPromptConfig.SystemPromptProvider;
import com.example.chat.dto.ChatRequest;
import com.example.chat.dto.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class for handling chat operations.
 * Delegates LLM communication to ChatClientWrapper.
 */
@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final ChatClientWrapper chatClientWrapper;
    private final SystemPromptProvider systemPromptProvider;
    private final ChatModel chatModel;
    
    @Value("${spring.ai.openai.chat.options.model:llama-3.3-70b-versatile}")
    private String modelName;

    public ChatService(ChatClientWrapper chatClientWrapper, 
                       SystemPromptProvider systemPromptProvider, 
                       ChatModel chatModel) {
        this.chatClientWrapper = chatClientWrapper;
        this.systemPromptProvider = systemPromptProvider;
        this.chatModel = chatModel;
    }

    /**
     * Processes a chat request and returns a response from the LLM.
     *
     * @param request the chat request containing the user's message
     * @return ChatResponse containing the LLM's response
     */
    public ChatResponse chat(ChatRequest request) {
        logger.info("Processing chat request: {}", request.getMessage());

        String userMessage = request.getMessage();
        Boolean enableSystemPrompt = request.getEnableSystemPrompt();

        String aiResponse;
        
        if (Boolean.TRUE.equals(enableSystemPrompt)) {
            aiResponse = chatClientWrapper.sendWithSystemPrompt(userMessage);
        } else {
            aiResponse = chatClientWrapper.sendWithoutSystemPrompt(userMessage);
        }

        logger.info("Received response from LLM using model: {}", modelName);

        return new ChatResponse(aiResponse, modelName, systemPromptProvider.getSystemPrompt());
    }

    /**
     * Simple chat method with just a message.
     *
     * @param message the user's message
     * @return the LLM's response as a string
     */
    public String chat(String message) {
        ChatRequest request = new ChatRequest(message);
        return chat(request).getResponse();
    }
}

