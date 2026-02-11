package dev.lanny.advisors_memoria.domain.model;

import java.time.Instant;

/**
 * Inmutable representation of  conversation message
 */
public final class Message {

    private final MessageRole role;
    private final String content;
    private final Instant timestamp;

    public Message(MessageRole role, String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        this.role = role;
        this.content = content;
        this.timestamp = Instant.now();
    }

    public  MessageRole role() {
        return role;
    }

    public String content() {
        return content;
    }

    public Instant timestamp() {
        return timestamp;
    }

 }
