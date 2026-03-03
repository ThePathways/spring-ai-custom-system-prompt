# spring-ai-custom-system-prompt

A Spring Boot application with Spring AI that provides a REST API to communicate with Large Language Models (LLM) using a custom system prompt loaded from a `.st` file.

## Features

- REST API for chat interactions with LLM
- Custom system prompt loaded from `system-prompt.st` file
- Support for enabling/disabling system prompt per request
- Standard project structure (Controller, Service, DTOs)
- Spring AI ChatClient integration
- Validation support
- Groq API integration (supports Llama, Mixtral, and other models)

## Prerequisites

- Java 21+
- Maven 3.9+
- Groq API Key (get one at https://console.groq.com)

## Project Structure

```
springai-customsystem/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/chat/
│   │   │   ├── SpringAiApplication.java
│   │   │   ├── client/
│   │   │   │   └── ChatClientWrapper.java
│   │   │   ├── config/
│   │   │   │   └── SystemPromptConfig.java
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java
│   │   │   ├── dto/
│   │   │   │   ├── ChatRequest.java
│   │   │   │   └── ChatResponse.java
│   │   │   └── service/
│   │   │       └── ChatService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── system-prompt.st
│   └── test/
└── README.md
```

## Configuration

1. Open `src/main/resources/application.properties`
2. Set your Groq API key:

```properties
spring.ai.openai.api-key=YOUR_GROQ_API_KEY
spring.ai.openai.base-url=https://api.groq.com/openai
spring.ai.openai.chat.options.model=llama-3.3-70b-versatile
```

Or use environment variable:
```bash
export GROQ_API_KEY=your-api-key-here
```

## Build & Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### POST /api/chat
Send a chat message with JSON body.

**Request:**
```json
{
  "message": "Hello, how are you?",
  "enableSystemPrompt": true
}
```

**Response:**
```json
{
  "response": "Hello! I'm doing well, thank you for asking...",
  "model": "llama-3.3-70b-versatile",
  "timestamp": "2026-03-03T13:28:36.585872",
  "systemPromptUsed": "You are a helpful and friendly AI assistant..."
}
```

### GET /api/chat?message=Hello
Simple chat endpoint using query parameter.

### GET /api/chat/simple?message=Hello
Returns just the response string.

### GET /api/chat/health
Health check endpoint.

## Customizing the System Prompt

Edit `src/main/resources/system-prompt.st` to customize the AI's behavior. The file supports template variables:

```
You are a helpful and friendly AI assistant. Your role is to provide accurate, clear, and concise responses to user questions. 

When answering:
- Be concise but thorough
- Use simple and understandable language
- If you're unsure about something, acknowledge it
- Provide practical examples when helpful

Current date: ${current_date}
```

## Supported Groq Models

The application supports various Groq models. To change the model, update the `application.properties`:

```properties
# Fast model for quick responses
spring.ai.openai.chat.options.model=llama-3.1-8b-instant

# Balanced model
spring.ai.openai.chat.options.model=llama-3.3-70b-versatile

# Most capable model
spring.ai.openai.chat.options.model=llama-3.2-90b-vision-preview

# Mixtral model
spring.ai.openai.chat.options.model=mixtral-8x7b-32768
```

## Dependencies

- Spring Boot 3.4.1
- Spring AI 1.0.0-M4
- OpenAI Chat Model (configured for Groq)
- Java 21

## License

This project is for educational purposes.

