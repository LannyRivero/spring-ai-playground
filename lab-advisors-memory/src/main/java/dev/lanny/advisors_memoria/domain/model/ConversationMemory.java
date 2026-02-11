package dev.lanny.advisors_memoria.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Aggregate that represents the memory of a conversation.
 *
 * <p>
 * This aggregate ensures that:
 * </p>
 * <ul>
 * <li>The conversation has a valid identifier</li>
 * <li>The memory size is bounded</li>
 * <li>The message history is exposed as read-only</li>
 * </ul>
 *
 * <p>
 * Memory growth is controlled to avoid uncontrolled prompt expansion,
 * which may increase token usage and latency.
 * </p>
 *
 * <p>
 * Thread-safety: This aggregate is NOT thread-safe by design.
 * Synchronization must be handled at application level if required.
 * </p>
 */
public class ConversationMemory {

    private final ConversationId id;
    private final int maxMessages;
    private final List<Message> messages;

    public ConversationMemory(ConversationId id, int maxMessages) {
        this.id = Objects.requireNonNull(id, "ConversationId cannot be null");

        if (maxMessages <= 0) {
            throw new IllegalArgumentException("maxMessages must be greater than zero");
        }

        this.maxMessages = maxMessages;
        this.messages = new ArrayList<>();
    }

    public ConversationId id() {
        return id;
    }

    /**
     * Appends a message to the conversation memory.
     * Applies FIFO trimming if memory exceeds configured limit.
     *
     * @param message message to append (must not be null)
     */
    public void append(Message message) {
        Objects.requireNonNull(message, "Message cannot be null");

        messages.add(message);
        enforceLimit();
    }

    /**
     * Returns the conversation history as an immutable list.
     */
    public List<Message> history() {
        return Collections.unmodifiableList(messages);
    }

    private void enforceLimit() {
        while (messages.size() > maxMessages) {
            messages.remove(0);
        }
    }
}
