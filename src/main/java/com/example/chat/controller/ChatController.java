package com.example.chat.controller;

import com.example.chat.dto.ChatRequest;
import com.example.chat.dto.ChatResponse;
import com.example.chat.service.ChatService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST Controller for handling chat requests.
 * Provides endpoints to communicate with the LLM.
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * POST /api/chat
     * Send a chat message to the LLM.
     *
     * @param request the chat request containing the message
     * @return ChatResponse with the LLM's response
     */
    @PostMapping
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        logger.info("Received chat request: {}", request.getMessage());
        
        ChatResponse response = chatService.chat(request);
        
        logger.info("Sending chat response");
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/chat
     * Simple chat endpoint using query parameter.
     *
     * @param message the message to send to the LLM
     * @return ChatResponse with the LLM's response
     */
    @GetMapping
    public ResponseEntity<ChatResponse> chatGet(@RequestParam String message) {
        logger.info("Received GET chat request: {}", message);
        
        ChatRequest request = new ChatRequest(message);
        ChatResponse response = chatService.chat(request);
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/chat/simple
     * Simple endpoint that returns just the response string.
     *
     * @param message the message to send to the LLM
     * @return Map with the response
     */
    @GetMapping("/simple")
    public ResponseEntity<Map<String, String>> simpleChat(@RequestParam String message) {
        logger.info("Received simple chat request: {}", message);
        
        String response = chatService.chat(message);
        
        return ResponseEntity.ok(Map.of("response", response));
    }

    /**
     * GET /api/chat/health
     * Health check endpoint.
     *
     * @return Map with status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}

