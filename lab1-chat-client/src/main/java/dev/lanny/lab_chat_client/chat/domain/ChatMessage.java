package dev.lanny.lab_chat_client.chat.domain;

/**
 * Domain value object representing a user chat message.
 *
 * <p>This abstraction allows the domain to remain independent
 * from transport-level DTOs.</p>
 */
public record ChatMessage(String content) {

    public ChatMessage {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Chat message must not be empty");
        }
    }
}

