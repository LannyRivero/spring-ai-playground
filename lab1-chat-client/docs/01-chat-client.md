# Lab 01 – AI Chat (Spring AI)

Este documento acompaña al **Lab 01 – AI Chat básico** y explica en mayor
detalle las decisiones técnicas, el diseño aplicado y los aprendizajes
obtenidos durante la integración inicial de un modelo de lenguaje (LLM)
en un backend Spring.

---

## 🤖 ¿Qué es un chat AI?

Un chat AI es una **interacción síncrona** entre un cliente y un modelo de
lenguaje (LLM), mediada por un backend que **mantiene el control total del flujo**.

En este enfoque, el backend:

- **No razona por el modelo**
- **No delega decisiones de negocio**
- **No confía en la IA como fuente de verdad**

La IA se trata como un **servicio externo**, similar a cualquier otra
dependencia de infraestructura.

---

## 🧠 Rol del backend

En este laboratorio, el backend asume las siguientes responsabilidades:

- Construir el prompt que se enviará al modelo
- Invocar al LLM mediante Spring AI
- Encapsular la respuesta en un contrato propio
- Gestionar errores, fallos de conectividad y límites del proveedor

El backend **no interpreta ni valida semánticamente la respuesta del modelo**;
simplemente la expone de forma controlada al cliente.

---

## 🏗️ Arquitectura aplicada

El laboratorio aplica una arquitectura por capas, inspirada en un enfoque
de **DDD ligero** y Clean Architecture:

- **Controller (API)**  
  Actúa como adaptador HTTP.  
  Recibe la petición, valida el input y delega en la capa de aplicación.

- **Application Service**  
  Orquesta la interacción con el modelo de lenguaje y encapsula el uso
  de Spring AI (`ChatClient`).

- **Domain**  
  Define conceptos básicos del dominio (por ejemplo, `ChatMessage`)
  y protege invariantes, evitando trabajar con tipos primitivos sin contexto.

- **LLM (Infraestructura)**  
  El modelo de lenguaje se trata como una dependencia externa,
  completamente aislada del dominio y de la API.

Esta separación permite:
- cambiar el proveedor de IA sin afectar al resto del sistema
- introducir nuevas capacidades de IA de forma incremental
- mantener el control del diseño backend

---

## ✍️ Prompting en este laboratorio

En este primer lab, el prompting es **intencionadamente mínimo**.

El prompt:
- se construye únicamente a partir del mensaje del usuario
- se envía al modelo desde la **capa de aplicación**

El objetivo no es optimizar la calidad del prompt, sino entender:

- **dónde vive el prompt en una arquitectura backend**
- **qué capa decide qué información se envía al LLM**

El prompting avanzado (system messages, reglas, reproducibilidad,
control de contexto) se abordará en laboratorios posteriores.

---

## 🚫 ¿Qué NO hace este laboratorio?

Este laboratorio **no implementa**:

- Memoria de conversación
- Retrieval Augmented Generation (RAG)
- Tools o ejecución de acciones
- Lógica de negocio basada en IA

Estas capacidades se incorporarán progresivamente en labs posteriores,
una vez consolidada la integración básica.

---

## 🧩 Dificultades y aprendizajes

Durante el desarrollo de este primer laboratorio surgieron varios retos
clave que ayudaron a consolidar los conceptos fundamentales de Spring AI.

Uno de los primeros aprendizajes fue **delimitar correctamente el alcance**
del lab. Resultó tentador introducir memoria, RAG o herramientas desde el
inicio, pero se decidió conscientemente mantener el ejemplo pequeño y
enfocado en una sola técnica: la integración básica con `ChatClient`.

Otro punto importante fue clarificar el concepto de *prompting* en un
contexto backend. Inicialmente puede asociarse a prompts complejos o reglas
avanzadas, pero el aprendizaje clave fue entender que el prompting comienza
en el momento en que el backend decide **qué mensaje se envía al modelo** y
**desde qué capa se construye**.

A nivel de diseño, introducir un objeto de dominio (`ChatMessage`) en lugar
de trabajar directamente con `String` permitió reforzar la separación entre
transporte, aplicación y dominio, y entender mejor el rol del controller
como adaptador entre capas.

Por último, trabajar inicialmente sin disponer de la API key ayudó a
diseñar la aplicación para **arrancar correctamente sin dependencias externas**
y fallar únicamente al invocar el LLM. Esto reforzó buenas prácticas de
seguridad, configuración por entorno y gestión de errores.

---

## 🎓 Conclusión

Este laboratorio sirve como **base sólida** para entender cómo integrar
IA en un backend Spring de forma:

- controlada
- extensible
- alineada con buenas prácticas de arquitectura

A partir de este punto, el proyecto está preparado para evolucionar hacia
casos más avanzados como memoria, RAG y herramientas, sin comprometer el
diseño inicial.
