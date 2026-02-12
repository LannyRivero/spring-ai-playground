package dev.lanny.advisors_memoria.application.service;

import dev.lanny.advisors_memoria.application.command.ChatCommand;
import dev.lanny.advisors_memoria.application.port.in.ChatUseCase;
import dev.lanny.advisors_memoria.application.port.out.ChatModelGateway;
import dev.lanny.advisors_memoria.application.port.out.ConversationMemoryStore;
import dev.lanny.advisors_memoria.application.result.ChatResult;
import dev.lanny.advisors_memoria.config.MemoryProperties;
import dev.lanny.advisors_memoria.domain.model.ConversationId;
import dev.lanny.advisors_memoria.domain.model.ConversationMemory;
import dev.lanny.advisors_memoria.domain.model.Message;
import dev.lanny.advisors_memoria.domain.model.MessageRole;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Application service responsible for handling chat interactions.
 *
 * <p>
 * This service orchestrates conversation memory retrieval,
 * message appending, and response generation.
 * </p>
 *
 * <p>
 * LLM integration will be added later through an adapter.
 * </p>
 */
@Service
public class ChatService implements ChatUseCase {

    private final ConversationMemoryStore memoryStore;
    private final MemoryProperties memoryProperties;
    private final ChatModelGateway chatModelGateway;

    public ChatService(ConversationMemoryStore memoryStore,
            MemoryProperties memoryProperties,
            ChatModelGateway chatModelGateway) {
        this.memoryStore = memoryStore;
        this.memoryProperties = memoryProperties;
        this.chatModelGateway = chatModelGateway;
    }

    @Override
    public ChatResult chat(ChatCommand command) {

        Objects.requireNonNull(command, "ChatCommand cannot be null");

        ConversationId conversationId = command.conversationId();

        // Retrieve or create memory
        ConversationMemory memory = memoryStore
                .findById(conversationId)
                .orElseGet(() -> new ConversationMemory(
                        conversationId,
                        memoryProperties.getMaxMessages()));

        // Append user message
        memory.append(new Message(MessageRole.USER, command.message()));

        // Generate assistant response (temporary stub)
        String assistantResponse = chatModelGateway.generateResponse(memory.history());

        // Append assistant response
        memory.append(new Message(MessageRole.ASSISTANT, assistantResponse));

        // Persist memory
        memoryStore.save(memory);

        return new ChatResult(
                assistantResponse,
                conversationId.value());
    }
    
}
