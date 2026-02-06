package dev.lanny.lab_chat_client.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(String userMessage) {
        try {
            return chatClient
                    .prompt()
                    .user(userMessage)
                    .call()
                    .content();
        } catch (Exception e) {
            return "LLM no disponible (API key no configurada o error de conexión).";
        }
    }

}
