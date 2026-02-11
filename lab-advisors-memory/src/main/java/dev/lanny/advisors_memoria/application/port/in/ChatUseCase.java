package dev.lanny.advisors_memoria.application.port.in;

import dev.lanny.advisors_memoria.application.command.ChatCommand;
import dev.lanny.advisors_memoria.application.result.ChatResult;

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
     * @param command chat command containing conversation id and message
     * @return ChatResult containing assistant response and conversation id
     */
    ChatResult chat(ChatCommand command);
}
