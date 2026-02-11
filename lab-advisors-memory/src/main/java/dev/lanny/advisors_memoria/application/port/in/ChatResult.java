package dev.lanny.advisors_memoria.application.port.in;

/**
 * Result returned by the ChatUseCase.
 *
 * @param response       assistant generated response
 * @param conversationId conversation identifier
 */
public record ChatResult(
        String response,
        String conversationId) {
}
