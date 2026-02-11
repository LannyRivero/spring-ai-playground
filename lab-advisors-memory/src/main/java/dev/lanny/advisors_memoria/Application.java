package dev.lanny.advisors_memoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import dev.lanny.advisors_memoria.config.MemoryProperties;

@SpringBootApplication
@EnableConfigurationProperties(MemoryProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
