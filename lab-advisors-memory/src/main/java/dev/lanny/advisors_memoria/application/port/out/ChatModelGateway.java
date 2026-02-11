package dev.lanny.advisors_memoria.application.port.out;

import java.util.List;
import dev.lanny.advisors_memoria.domain.model.Message;

/**
 * Secondary port responsible for generating AI responses.
 */
public interface ChatModelGateway {

    /**
     * Generates a response from the language model.
     *
     * @param conversationHistory full conversation history
     * @return generated assistant response
     */
    String generateResponse(List<Message> conversationHistory);
}
