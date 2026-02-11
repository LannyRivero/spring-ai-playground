package dev.lanny.advisors_memoria.application.result;

/**
 * Result returned by the ChatUseCase.
 *
 * <p>
 * Encapsulates the assistant response and conversation identifier.
 * </p>
 */
public record ChatResult(
                String response,
                String conversationId) {
}
