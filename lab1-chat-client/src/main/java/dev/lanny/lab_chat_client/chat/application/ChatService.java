package dev.lanny.lab_chat_client.chat.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import dev.lanny.lab_chat_client.chat.domain.ChatMessage;

/**
 * Application service responsible for interacting with a Large Language Model
 * (LLM)
 * using Spring AI ChatClient.
 *
 * <p>
 * This service encapsulates all AI-related concerns and provides a simple
 * interface for sending user messages to the model.
 * </p>
 *
 * <p> Design considerations:
 * <ul>
 * <li>The controller layer must not depend on Spring AI or OpenAI
 * directly.</li>
 * <li>The LLM is treated as an external infrastructure dependency.</li>
 * <li>Failures (missing API key, connectivity issues) are handled
 * gracefully.</li>
 * </ul>
 *
 * <p>
 * This implementation corresponds to Lab 01: basic chat without memory,
 * retrieval (RAG), or tools.
 * </p>
 */

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(ChatMessage userMessage) {
        try {
            return chatClient
                    .prompt()
                    .user(userMessage.content())
                    .call()
                    .content();
        } catch (Exception ex) {
            log.error("LLM invocation failed", ex);
            throw new ChatUnavailableException(
                    "LLM is currently unavailable",
                    ex
            );
        }
    }
}