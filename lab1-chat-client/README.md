# Lab 01 – AI Chat básico (Spring AI)

Este laboratorio forma parte del repositorio **spring-ai-playground** y tiene como
objetivo familiarizarse con los fundamentos de Spring AI a través de un ejemplo
mínimo y controlado.

## Objetivo
- Entender cómo conectar un LLM usando Spring AI `ChatClient`
- Comprender dónde vive el prompting en una arquitectura backend
- Encapsular la IA como una dependencia externa
- Aplicar buenas prácticas de seguridad y diseño

## Descripción
El lab implementa un chat AI básico expuesto mediante un endpoint REST.
El backend actúa como intermediario entre el usuario y el modelo de lenguaje,
sin delegar decisiones de negocio ni confiar en la IA como fuente de verdad.

La integración sigue la documentación oficial de Spring AI y utiliza un modelo
pequeño para controlar el consumo de tokens.

## Arquitectura
- API: controllers y DTOs (HTTP)
- Application: orquestación de la interacción con el LLM
- Domain: conceptos básicos del dominio (mensaje del usuario)
- Infraestructura: LLM gestionado mediante Spring AI

## Prompting
En este laboratorio el prompting es intencionadamente mínimo y se limita al
mensaje del usuario. El objetivo es entender el punto de integración con el LLM
antes de introducir reglas o contexto adicional.

## Qué NO incluye este lab
- Memoria de conversación
- RAG (retrieval augmented generation)
- Tools o ejecución de acciones
- Structured output

Estas capacidades se explorarán en laboratorios posteriores.

## Requisitos
- Java 21
- Spring Boot 3.x
- Spring AI 1.1.x
- API key de OpenAI configurada por entorno

⚠️ La API key **no debe incluirse en el código ni en ficheros de configuración**.

## Ejecución
1. Configurar la variable de entorno:
```bash
   export SPRING_AI_OPENAI_API_KEY=your_api_key
```

2. Ejecutar la aplicación:
```bash
   mvn spring-boot:run

```

3. Probar el endpoint:
```bash
   POST /api/chat
{
  "message": "Explícame qué es Spring AI en una frase"
```

## Documentación adicional

Para una explicación más detallada del diseño, las decisiones tomadas y los
aprendizajes obtenidos, ver:

- docs/01-chat-client.md
