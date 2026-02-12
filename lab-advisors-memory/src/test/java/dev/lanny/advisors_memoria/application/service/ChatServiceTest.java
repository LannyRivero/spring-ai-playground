package dev.lanny.advisors_memoria.application.service;

import dev.lanny.advisors_memoria.application.command.ChatCommand;
import dev.lanny.advisors_memoria.application.port.out.ChatModelGateway;
import dev.lanny.advisors_memoria.application.port.out.ConversationMemoryStore;
import dev.lanny.advisors_memoria.application.result.ChatResult;
import dev.lanny.advisors_memoria.config.MemoryProperties;
import dev.lanny.advisors_memoria.domain.model.ConversationId;
import dev.lanny.advisors_memoria.domain.model.ConversationMemory;
import dev.lanny.advisors_memoria.domain.model.MessageRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    private ConversationMemoryStore memoryStore;
    private ChatModelGateway chatModelGateway;
    private MemoryProperties memoryProperties;
    private ChatService chatService;

    @BeforeEach
    void setUp() {
        memoryStore = mock(ConversationMemoryStore.class);
        chatModelGateway = mock(ChatModelGateway.class);

        memoryProperties = new MemoryProperties();
        memoryProperties.setMaxMessages(5);

        chatService = new ChatService(
                memoryStore,
                memoryProperties,
                chatModelGateway
        );
    }

    @Test
    @DisplayName("Should create new memory when conversation does not exist")
    void shouldCreateNewMemoryIfNotExists() {

        ConversationId id = ConversationId.generate();
        ChatCommand command = new ChatCommand(id, "Hola");

        when(memoryStore.findById(id)).thenReturn(Optional.empty());
        when(chatModelGateway.generateResponse(any()))
                .thenReturn("Respuesta IA");

        ChatResult result = chatService.chat(command);

        assertThat(result.response()).isEqualTo("Respuesta IA");
        assertThat(result.conversationId()).isEqualTo(id.value());

        verify(memoryStore).save(any(ConversationMemory.class));
    }

    @Test
    @DisplayName("Should append user and assistant messages to existing memory")
    void shouldAppendMessagesToExistingMemory() {

        ConversationId id = ConversationId.generate();
        ChatCommand command = new ChatCommand(id, "Hola");

        ConversationMemory memory = new ConversationMemory(id, 5);

        when(memoryStore.findById(id)).thenReturn(Optional.of(memory));
        when(chatModelGateway.generateResponse(any()))
                .thenReturn("Respuesta IA");

        chatService.chat(command);

        assertThat(memory.history()).hasSize(2);
        assertThat(memory.history().get(0).role()).isEqualTo(MessageRole.USER);
        assertThat(memory.history().get(1).role()).isEqualTo(MessageRole.ASSISTANT);

        verify(memoryStore).save(memory);
    }

    @Test
    @DisplayName("Should enforce memory limit (FIFO trimming)")
    void shouldEnforceMemoryLimit() {

        memoryProperties.setMaxMessages(2);

        ConversationId id = ConversationId.generate();
        ConversationMemory memory = new ConversationMemory(id, 2);

        when(memoryStore.findById(id)).thenReturn(Optional.of(memory));
        when(chatModelGateway.generateResponse(any()))
                .thenReturn("Respuesta IA");

        chatService.chat(new ChatCommand(id, "Uno"));
        chatService.chat(new ChatCommand(id, "Dos"));
        chatService.chat(new ChatCommand(id, "Tres"));

        // Max 2 messages -> last user + last assistant
        assertThat(memory.history()).hasSize(2);

        verify(memoryStore, times(3)).save(memory);
    }

    @Test
    @DisplayName("Should pass conversation history to ChatModelGateway")
    void shouldPassHistoryToGateway() {

        ConversationId id = ConversationId.generate();
        ConversationMemory memory = new ConversationMemory(id, 5);

        when(memoryStore.findById(id)).thenReturn(Optional.of(memory));
        when(chatModelGateway.generateResponse(any()))
                .thenReturn("Respuesta IA");

        chatService.chat(new ChatCommand(id, "Hola"));

        verify(chatModelGateway, times(1))
                .generateResponse(memory.history());
    }

    @Test
    @DisplayName("Should throw exception when command is null")
    void shouldThrowIfCommandIsNull() {

        assertThatThrownBy(() -> chatService.chat(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("ChatCommand cannot be null");
    }
}

