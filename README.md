# Spring AI Playground

Repositorio de aprendizaje práctico para familiarizarse con los fundamentos de Spring AI.

## Objetivo
- Entender y aplicar conceptos básicos de Spring AI
- Desarrollar ejemplos pequeños y controlados (1 técnica por lab)
- Documentar aprendizajes y dificultades
- Proponer posibles casos de uso para el ecosistema UNADA

## Stack
- Java 17
- Spring Boot 3.x
- Spring AI 1.1.x
- OpenAI API (modelo pequeño: gpt-5-mini / gpt-5-nano)
- PostgreSQL + pgvector

## Seguridad
⚠️ La API Key de OpenAI **NO** se incluye en el código bajo ninguna circunstancia.  
Se configura únicamente mediante variables de entorno en local.

## Estructura del repo
Cada carpeta `lab-*` contiene una mini-aplicación centrada en un concepto concreto.

## Labs (plan)
1. lab-chat-client (ChatClient básico + prompting)
2. lab-advisors-memory (Advisors + memoria)
3. lab-structured-output (respuestas tipadas / JSON)
4. lab-rag-pgvector (vector store + retrieval)
5. lab-etl (ingesta desde texto/pdf/web)
6. lab-tools (tools / function calling)

