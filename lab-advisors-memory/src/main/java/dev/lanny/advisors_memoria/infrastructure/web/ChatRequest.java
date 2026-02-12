package dev.lanny.advisors_memoria.infrastructure.web;

public record ChatRequest(
        String conversationId,
        String message) {
}
