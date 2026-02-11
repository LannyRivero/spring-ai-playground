package dev.lanny.advisors_memoria.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aggregate that represents the memory of a conversation.
 *
 * <p> It guarantee bounded memory size to avoid uncontrolled prompt growth</p>
 */
public class ConversationMemory {
    private final ConversationId id;
    private  final int maxMessages;
    private final List<Message> messages = new ArrayList<>();

    public  ConversationMemory(ConversationId id, int maxMessages) {
        this.id = id;
        this.maxMessages = maxMessages;
    }

    public ConversationId id() {
        return id;
    }
    public void addMessage(Message message) {
        messages.add(message);
        enforceLimit();
    }

    public List<Message> history() {
        return Collections.unmodifiableList(messages);
    }

    private void enforceLimit() {
        while (messages.size() > maxMessages) {
            messages.remove(0);
        }
    }
}
