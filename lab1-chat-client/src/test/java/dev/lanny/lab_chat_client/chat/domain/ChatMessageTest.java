package dev.lanny.lab_chat_client.chat.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    @Test
    void shouldCreateChatMessageWithValidContent() {
        // Given
        String validContent = "Hello, how can I help you?";

        // When
        ChatMessage message = new ChatMessage(validContent);

        // Then
        assertNotNull(message);
        assertEquals(validContent, message.content());
    }

    @Test
    void shouldRejectNullContent() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ChatMessage(null)
        );
        assertEquals("Chat message must not be empty", exception.getMessage());
    }

    @Test
    void shouldRejectBlankContent() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ChatMessage("   ")
        );
        assertEquals("Chat message must not be empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyStringContent() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ChatMessage("")
        );
        assertEquals("Chat message must not be empty", exception.getMessage());
    }
}
