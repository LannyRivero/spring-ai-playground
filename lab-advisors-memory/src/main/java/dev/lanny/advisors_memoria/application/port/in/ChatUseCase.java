package dev.lanny.advisors_memoria.application.port.in;

import dev.lanny.advisors_memoria.application.result.ChatResult;
import dev.lanny.advisors_memoria.domain.model.ConversationId;

/**
 * Primary port (input port) for chat interactions.
 *
 * <p>This use case represents the entry point for handling
 * conversational requests within the system.</p>
 *
 * <p>It orchestrates memory retrieval, LLM invocation,
 * and response persistence.</p>
 */
public interface ChatUseCase {

    /**
     * Processes a chat message within a given conversation.
     *
     * @param conversationId unique conversation identifier
     * @param message        user message (must not be null or blank)
     * @return ChatResult containing assistant response and conversation id
     */
    ChatResult chat(ConversationId conversationId, String message);
}
