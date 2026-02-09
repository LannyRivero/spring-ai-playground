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
Este laboratorio aplica una arquitectura por capas, inspirada en DDD ligero:
- Controller: capa de entrada (HTTP), responsable únicamente de recibir la petición y devolver la respuesta.
- Application Service: orquesta la interacción con el modelo de lenguaje y encapsula el uso de Spring AI.
- Domain: define conceptos básicos del dominio (por ejemplo, el mensaje del usuario) y protege invariantes.
- LLM: tratado como una dependencia externa de infraestructura.

Esta separación permite cambiar o extender la integración con IA sin afectar al resto del sistema.

## Prompting en este laboratorio

En este lab el prompting es intencionadamente mínimo.
El prompt se construye únicamente a partir del mensaje del usuario y se envía al modelo desde la capa de aplicación.

El objetivo es entender:

- dónde vive el prompt en una arquitectura backend

- cómo el backend controla qué información se envía al LLM

El prompting avanzado (system messages, reglas, reproducibilidad) se aborda en laboratorios posteriores.

## ¿Qué NO hace este lab?
Este laboratorio no implementa:
- memoria de conversación
- recuperación de información (RAG)
- tools o ejecución de acciones
- lógica de negocio basada en IA

Estas capacidades se exploran progresivamente en labs posteriores.

## Dificultades y aprendizajes
Durante el desarrollo de este primer laboratorio surgieron varias dificultades que ayudaron a consolidar los conceptos básicos de Spring AI.

Al inicio, una de las principales dudas fue delimitar correctamente el alcance del lab y evitar introducir conceptos como memoria, RAG o tools antes de entender bien la integración básica con ChatClient. Esto llevó a reforzar la idea de construir pequeños ejemplos enfocados en una sola técnica.

También fue necesario aclarar el concepto de prompting en un contexto backend. Inicialmente podía asociarse a prompts complejos o reglas avanzadas, pero el aprendizaje clave fue entender que el prompting comienza en el momento en que el backend decide qué mensaje se envía al modelo y desde qué capa se construye el prompt.

A nivel de diseño, introducir un objeto de dominio (ChatMessage) en lugar de trabajar directamente con String ayudó a reforzar la separación entre transporte, aplicación y dominio, y a entender mejor el rol del controller como adaptador entre capas.

Por último, trabajar sin disponer inicialmente de la API key permitió diseñar la aplicación para arrancar correctamente sin dependencias externas y fallar únicamente al invocar el LLM, reforzando buenas prácticas de seguridad y configuración por entorno.

En conjunto, este laboratorio ha servido como base sólida para entender cómo integrar IA en un backend Spring de forma controlada y extensible.