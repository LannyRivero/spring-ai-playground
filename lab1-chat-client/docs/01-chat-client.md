# Lab 01 – AI Chat (Spring AI)

## ¿Qué es un chat AI?
Un chat AI es una interacción síncrona entre un usuario y un modelo de lenguaje (LLM),
donde el backend actúa como intermediario controlado.

El backend:
- no razona por el modelo
- no delega decisiones de negocio
- no confía en la IA como fuente de verdad

## Rol del backend
- Construir el prompt
- Invocar al modelo
- Validar y encapsular la respuesta
- Gestionar errores y límites

## Arquitectura aplicada
- Controller: capa de entrada (HTTP)
- Application Service: orquesta la interacción con la IA
- LLM: dependencia externa (infraestructura)

## ¿Qué NO hace este lab?
- No mantiene memoria de conversación
- No recupera información (RAG)
- No ejecuta acciones (tools)

Estas capacidades se exploran en labs posteriores.
