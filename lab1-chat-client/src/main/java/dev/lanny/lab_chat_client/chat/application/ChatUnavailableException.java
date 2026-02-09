package dev.lanny.lab_chat_client.chat.application;

public class ChatUnavailableException extends RuntimeException {

    public ChatUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
