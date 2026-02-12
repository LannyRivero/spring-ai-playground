package dev.lanny.advisors_memoria.infrastructure.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lanny.advisors_memoria.application.command.ChatCommand;
import dev.lanny.advisors_memoria.application.port.in.ChatUseCase;
import dev.lanny.advisors_memoria.application.result.ChatResult;
import dev.lanny.advisors_memoria.domain.model.ConversationId;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatUseCase chatUseCase;

    public ChatController(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @PostMapping
    public ChatResult chat(@RequestBody ChatRequest request) {

        ConversationId id = (request.conversationId() == null || request.conversationId().isBlank())
                ? ConversationId.generate()
                : ConversationId.of(request.conversationId());

        ChatCommand command = new ChatCommand(id, request.message());

        return chatUseCase.chat(command);
    }

}
