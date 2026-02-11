package dev.lanny.advisors_memoria.domain.model;


import java.util.Objects;
import java.util.UUID;

/**
 * Value Object that uniquely identifies a conversation.
 *
 * <p>This object is inmutable and guarantees that a valid identifier
 * is always present.</p>
 * */
public  final class ConversationId {
    private final String value;

    private ConversationId(String value) {
      this.value = Objects.requireNonNull(value, "ConversationId cannot be null");
    }

    public  static ConversationId generate() {
        return new ConversationId(UUID.randomUUID().toString());
    }

    public static ConversationId of(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("ConversationId cannot be empty");
        }
        return new ConversationId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  ConversationId that)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
