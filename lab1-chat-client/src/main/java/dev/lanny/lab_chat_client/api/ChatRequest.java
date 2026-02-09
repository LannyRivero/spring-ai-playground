package dev.lanny.lab_chat_client.api;

import jakarta.validation.constraints.NotBlank;

/**
 * HTTP request payload for the chat endpoint.
 *
 * <p>
 * Validation at this level improves API ergonomics but does not replace
 * domain-level validation.
 * </p>
 */
public record ChatRequest(
        @NotBlank(message = "Message must not be blank") String message) {
}
