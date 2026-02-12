package dev.lanny.advisors_memoria.infrastructure.ai;

import dev.lanny.advisors_memoria.application.port.out.ChatModelGateway;
import dev.lanny.advisors_memoria.domain.model.Message;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class StubChatModelGateway implements ChatModelGateway {

    @Override
    public String generateResponse(List<Message> conversationHistory) {
        Message last = conversationHistory.get(conversationHistory.size() - 1);
        return "Echo: " + last.content();
    }
}
