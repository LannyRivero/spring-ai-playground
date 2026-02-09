package dev.lanny.lab_chat_client.api;

import org.springframework.web.bind.annotation.*;

import dev.lanny.lab_chat_client.chat.application.ChatService;
import dev.lanny.lab_chat_client.chat.domain.ChatMessage;

/**
 * REST controller exposing a minimal HTTP API for interacting with an AI chat.
 *
 * <p>
 * This controller acts purely as an input adapter. It translates HTTP requests
 * into domain concepts and delegates all business and AI-related logic to the
 * application layer.
 * </p>
 *
 * <p>
 * Responsibilities:
 * <ul>
 * <li>Accept HTTP requests</li>
 * <li>Map request DTOs to domain objects</li>
 * <li>Return HTTP responses</li>
 * </ul>
 *
 * <p>
 * It deliberately contains no validation logic, no AI concerns, and no error
 * handling beyond delegation.
 * </p>
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        ChatMessage message = new ChatMessage(request.message());
        String response = chatService.chat(message);

        return new ChatResponse(response);
    }
}
