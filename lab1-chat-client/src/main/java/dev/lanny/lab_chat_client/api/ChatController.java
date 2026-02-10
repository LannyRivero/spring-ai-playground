package dev.lanny.lab_chat_client.api;

import org.springframework.web.bind.annotation.*;

import dev.lanny.lab_chat_client.chat.application.ChatService;
import dev.lanny.lab_chat_client.chat.domain.ChatMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

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

    @Operation(summary = "Send a message to the AI chat", description = """
            Sends a user message to the AI model and returns a generated response.

            This endpoint demonstrates basic prompting using Spring AI ChatClient.
            It does not include memory, retrieval (RAG), or tools.
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "AI response generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or blank message"),
            @ApiResponse(responseCode = "503", description = "AI service unavailable")
    })

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        ChatMessage message = new ChatMessage(request.message());
        String response = chatService.chat(message);

        return new ChatResponse(response);
    }
}
