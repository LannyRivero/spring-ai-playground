package dev.lanny.lab_chat_client.api;

import org.springframework.web.bind.annotation.*;

import dev.lanny.lab_chat_client.chat.ChatService;

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
