package dev.lanny.advisors_memoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import dev.lanny.advisors_memoria.config.MemoryProperties;

@SpringBootApplication(
    excludeName = {
        "org.springframework.ai.model.openai.autoconfigure.OpenAiChatAutoConfiguration",
        "org.springframework.ai.model.openai.autoconfigure.OpenAiEmbeddingAutoConfiguration",
        "org.springframework.ai.model.openai.autoconfigure.OpenAiAudioSpeechAutoConfiguration",
        "org.springframework.ai.model.openai.autoconfigure.OpenAiAudioTranscriptionAutoConfiguration",
        "org.springframework.ai.model.openai.autoconfigure.OpenAiModerationAutoConfiguration",
        "org.springframework.ai.model.openai.autoconfigure.OpenAiImageAutoConfiguration"
    }
)
@EnableConfigurationProperties(MemoryProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
