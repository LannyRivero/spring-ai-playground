package dev.lanny.lab_chat_client.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.lanny.lab_chat_client.chat.application.ChatService;
import dev.lanny.lab_chat_client.chat.domain.ChatMessage;

/**
 * REST controller exposing a minimal HTTP API for interacting with an AI chat.
 *
 * <p>
 * This controller acts as an input adapter and delegates all AI-related logic
 * to the application service layer.
 * </p>
 * It deliberately contains no knowledge about:
 * </p>
 * <p>
 * It deliberately contains no knowledge about:
 * </p>
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

        if (request == null || request.message() == null || request.message().isBlank()) {
            throw new IllegalArgumentException("Message must not be blank.");
        }

        ChatMessage message = new ChatMessage(request.message());

        String response = chatService.chat(message);

        return new ChatResponse(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ex.getMessage()));
    }

    private record ErrorResponse(String error) {
    }
}
