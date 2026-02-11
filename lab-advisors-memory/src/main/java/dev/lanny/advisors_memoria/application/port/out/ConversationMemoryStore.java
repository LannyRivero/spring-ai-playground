package dev.lanny.advisors_memoria.application.port.out;

import java.util.Optional;

import dev.lanny.advisors_memoria.domain.model.ConversationId;
import dev.lanny.advisors_memoria.domain.model.ConversationMemory;

/**
 * Secondary port responsible for conversation memory storage.
 *
 * <p>
 * This abstraction allows the application layer to store and retrieve
 * conversation memory without being coupled to any specific persistence
 * technology.
 * </p>
 *
 * <p>
 * Implementations may use in-memory storage, relational databases,
 * distributed caches, or other mechanisms.
 * </p>
 */
public interface ConversationMemoryStore {

    /**
     * Retrieves the conversation memory for the given identifier.
     *
     * @param id unique conversation identifier (must not be null)
     * @return Optional containing the conversation memory if present
     */
    Optional<ConversationMemory> findById(ConversationId id);

    /**
     * Persists the given conversation memory aggregate.
     *
     * @param memory conversation memory to store (must not be null)
     */
    void save(ConversationMemory memory);

    /**
     * Deletes the conversation memory associated with the given identifier.
     *
     * @param id unique conversation identifier
     */
    void delete(ConversationId id);
}
