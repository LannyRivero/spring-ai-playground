package dev.lanny.lab_chat_client.api;

import org.springframework.web.bind.annotation.*;

import dev.lanny.lab_chat_client.chat.ChatService;

/**
 * REST controller exposing a minimal HTTP API for interacting with an AI chat.
 *
 * <p>
 * This controller acts as an input adapter and delegates all AI-related logic
 * to the application service layer.
 * </p>
 *
 * <p>
 * It deliberately contains no knowledge about:
 * <ul>
 * <li>LLM providers</li>
 * <li>Prompt construction</li>
 * <li>Spring AI internals</li>
 * </ul>
 *
 * <p>
 * This keeps the HTTP layer stable even if the AI implementation changes.
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
        String response = chatService.chat(request.message());
        return new ChatResponse(response);
    }
}
