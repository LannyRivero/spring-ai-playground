package dev.lanny.advisors_memoria.config;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties related to conversation memory.
 *
 * <p>Binds properties under the prefix {@code memory}.</p>
 */
@Validated
@ConfigurationProperties(prefix = "memory")
public class MemoryProperties {

    @Min(value = 1, message = "max-messages must be greater than 0")
    private int maxMessages;

    public int getMaxMessages() {
        return maxMessages;
    }

    public void setMaxMessages(int maxMessages) {
        this.maxMessages = maxMessages;
    }
}

