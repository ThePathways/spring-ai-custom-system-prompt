package com.example.chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for ChatClient and System Prompt.
 * Loads the system prompt from a .st (StringTemplate) file.
 */
@Configuration
public class SystemPromptConfig {

    @Value("classpath:system-prompt.st")
    private Resource systemPromptResource;

    /**
     * Loads the system prompt from the .st file with variable substitution.
     */
    public String loadSystemPrompt() {
        try {
            String template = new String(systemPromptResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            
            // Replace template variables
            Map<String, String> variables = new HashMap<>();
            variables.put("current_date", LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            
            // Simple template variable replacement
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                template = template.replace("${" + entry.getKey() + "}", entry.getValue());
            }
            
            return template;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load system prompt from file", e);
        }
    }

    /**
     * Creates a ChatClient with the custom system prompt.
     */
    @Bean
    public ChatClient chatClient(OpenAiChatModel chatModel) {
        String systemPrompt = loadSystemPrompt();
        
        return ChatClient.builder(chatModel)
                .defaultSystem(systemPrompt)
                .build();
    }

    /**
     * Provides access to the system prompt for other components.
     */
    @Bean
    public SystemPromptProvider systemPromptProvider() {
        return new SystemPromptProvider(loadSystemPrompt());
    }

    /**
     * Helper class to provide the system prompt.
     */
    public static class SystemPromptProvider {
        private final String systemPrompt;

        public SystemPromptProvider(String systemPrompt) {
            this.systemPrompt = systemPrompt;
        }

        public String getSystemPrompt() {
            return systemPrompt;
        }
    }
}

