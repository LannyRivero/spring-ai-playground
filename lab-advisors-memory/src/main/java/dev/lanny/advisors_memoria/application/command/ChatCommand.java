package dev.lanny.advisors_memoria.application.command;

import dev.lanny.advisors_memoria.domain.model.ConversationId;

/**
 * Command object representing a chat request.
 *
 * <p>
 * This object encapsulates the input required to process
 * a chat interaction.
 * </p>
 */
public record ChatCommand(
        ConversationId conversationId,
        String message) {

    public ChatCommand {
        if (conversationId == null) {
            throw new IllegalArgumentException("ConversationId cannot be null");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
    }
}
