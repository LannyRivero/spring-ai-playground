package dev.lanny.advisors_memoria.infrastructure.persistence;

import dev.lanny.advisors_memoria.application.port.out.ConversationMemoryStore;
import dev.lanny.advisors_memoria.domain.model.ConversationId;
import dev.lanny.advisors_memoria.domain.model.ConversationMemory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryConversationMemoryStore implements ConversationMemoryStore {

    private final Map<String, ConversationMemory> store = new ConcurrentHashMap<>();

    @Override
    public Optional<ConversationMemory> findById(ConversationId id) {
        return Optional.ofNullable(store.get(id.value()));
    }

    @Override
    public void save(ConversationMemory memory) {
        store.put(memory.id().value(), memory);
    }

    @Override
    public void delete(ConversationId id) {
        store.remove(id.value());
    }
}
