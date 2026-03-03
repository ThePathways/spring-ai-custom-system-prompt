package com.example.chat.client;

import com.example.chat.config.SystemPromptConfig.SystemPromptProvider;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/**
 * Wrapper around Spring AI ChatClient.
 * Handles communication with the LLM.
 */
@Component
public class ChatClientWrapper {

    private final ChatClient chatClient;
    private final SystemPromptProvider systemPromptProvider;

    public ChatClientWrapper(ChatClient chatClient, SystemPromptProvider systemPromptProvider) {
        this.chatClient = chatClient;
        this.systemPromptProvider = systemPromptProvider;
    }

    /**
     * Send a message to the LLM with the system prompt.
     *
     * @param userMessage the user's message
     * @return the LLM's response
     */
    public String sendWithSystemPrompt(String userMessage) {
        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    /**
     * Send a message to the LLM without the system prompt.
     *
     * @param userMessage the user's message
     * @return the LLM's response
     */
    public String sendWithoutSystemPrompt(String userMessage) {
        return chatClient.prompt()
                .system(systemPromptProvider.getSystemPrompt())
                .user(userMessage)
                .call()
                .content();
    }
}

