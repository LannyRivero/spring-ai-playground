package dev.lanny.lab_chat_client.chat.domain;

/**
 * Domain value object representing a user chat message.
 *
 * <p>It enforces basic invariants (non-null, non-blank) and protects
 * the application layer from dealing with raw strings.</p>
 *
 * <p>In later labs, this object may evolve to include additional
 * metadata such as role, language, or context.</p>
 */

public record ChatMessage(String content) {

    public ChatMessage {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Chat message must not be empty");
        }
    }
}

