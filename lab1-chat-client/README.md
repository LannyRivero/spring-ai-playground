# Lab 01 – AI Chat básico (Spring AI)

Este laboratorio forma parte del repositorio **spring-ai-playground** y tiene como
objetivo explorar la integración de **Large Language Models (LLMs)** en un backend
Java utilizando **Spring AI**, a través de un ejemplo **mínimo, controlado y alineado
con buenas prácticas de arquitectura backend**.

Este proyecto está pensado tanto como **ejercicio de aprendizaje** como
**pieza demostrable de portfolio técnico**.

---

## 🎯 Objetivo

- Integrar un LLM utilizando Spring AI y `ChatClient`
- Entender **dónde y cómo** se conecta la IA dentro de una arquitectura backend
- Tratar la IA como una **dependencia externa**, no como lógica de negocio
- Aplicar buenas prácticas de:
  - separación de capas
  - seguridad
  - configuración por entorno

---

## 📖 Descripción

El laboratorio implementa un **chat AI básico** expuesto mediante un endpoint REST.

El backend actúa como **intermediario entre el cliente y el modelo de lenguaje**,
limitándose a orquestar la llamada al LLM sin delegar decisiones de negocio ni
considerar la IA como fuente de verdad.

La integración sigue la **documentación oficial de Spring AI** y utiliza un modelo
ligero para mantener el consumo de tokens bajo control.

---

## 🏗️ Arquitectura

El proyecto sigue una separación clara de responsabilidades:

- **API**  
  Controllers y DTOs responsables únicamente del contrato HTTP.

- **Application**  
  Orquestación de la interacción con el modelo de lenguaje.

- **Domain**  
  Conceptos básicos del dominio (por ejemplo, el mensaje del usuario).

- **Infrastructure**  
  Integración con el proveedor de IA mediante Spring AI.

Este enfoque permite:
- bajo acoplamiento
- facilidad para sustituir el proveedor de IA
- mayor testabilidad
- evolución controlada del sistema
- 
---
## 🔄 Flujo de la petición

El flujo completo de una petición al endpoint de chat es el siguiente:
HTTP Request
│
▼
ChatController
│
▼
ChatService
│
▼
Spring AI ChatClient
│
▼
LLM (OpenAI)

El modelo de lenguaje se trata explícitamente como una **infraestructura externa**,
siguiendo principios de diseño backend y Clean Architecture.

---
## ✍️ Prompting

En este laboratorio el prompting es **intencionadamente mínimo** y se limita al
mensaje proporcionado por el usuario.

El objetivo es entender **el punto de integración con el LLM** antes de introducir:
- reglas de sistema
- contexto adicional
- memoria de conversación

---

## 🚫 Qué NO incluye este lab

- Memoria de conversación
- RAG (Retrieval Augmented Generation)
- Tools o ejecución de acciones
- Structured output

Estas capacidades se explorarán en **laboratorios posteriores** del repositorio.

---

## ✅ Requisitos

- Java 21
- Spring Boot 3.x
- Spring AI 1.1.x
- API key de OpenAI configurada por entorno

⚠️ **La API key no debe incluirse nunca en el código ni en ficheros de configuración.**

---

## 🔐 Configuración de la API key

El proyecto utiliza Spring AI con OpenAI como proveedor del modelo.
La API key debe configurarse como **variable de entorno**.

Ejemplo usando un fichero `.env` (no versionado):

```bash
SPRING_AI_OPENAI_API_KEY=sk-xxxx
```

Cargar las variables en la sesión actual:
```bash
set -a
source .env
set +a
```

Verificar que la variable está disponible:
```bash
echo $SPRING_AI_OPENAI_API_KEY  
```
Spring AI leerá automáticamente esta variable al iniciar la aplicación.

## ▶️Ejecución

Iniciar la aplicación:
```bash
   mvn spring-boot:run
```

Probar el endpoint vía curl:
```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "Explícame qué es Spring AI en una frase"
  }'
 
```
La respuesta contendrá el texto generado por el modelo.


## API Documentation (Swagger)

El proyecto expone su contrato REST mediante OpenAPI / Swagger, lo que permite
explorar y probar el endpoint sin necesidad de herramientas externas como Postman.

Una vez levantada la aplicación, la documentación interactiva está disponible en:
http://localhost:8080/swagger-ui.html


### Acceso a Swagger UI

Al acceder a la URL se muestra la lista de endpoints disponibles.  
En este laboratorio se expone un único endpoint de chat:

![Swagger – listado de endpoints](docs/images/swagger-chat-endpoint.png)

---

### Probar el endpoint desde Swagger

Swagger permite ejecutar peticiones directamente desde el navegador.

Ejemplo de **request body** esperado por el endpoint `/api/chat`:

![Swagger – request body](docs/images/swagger-request.png)

---

### Respuesta del servidor

La respuesta contiene el texto generado por el modelo de lenguaje:

![Swagger – response](docs/images/swagger-response.png)

---

Desde Swagger es posible:
- Explorar el contrato REST
- Validar el formato de entrada y salida
- Ejecutar peticiones reales contra el backend

El uso de Swagger en este laboratorio refuerza el enfoque **API-first** y permite
validar el comportamiento del sistema como una **caja negra**, alineado con
buenas prácticas de desarrollo backend.

📌 **Nota**
  
 Las capturas de Swagger incluidas en este documento corresponden a una
 ejecución **local** del proyecto (`localhost`) con la aplicación levantada
 mediante `mvn spring-boot:run` y una API key configurada por variable de entorno.


## Documentación adicional

Para una explicación más detallada del diseño, las decisiones tomadas y los
aprendizajes obtenidos, ver:

- docs/01-chat-client.md

