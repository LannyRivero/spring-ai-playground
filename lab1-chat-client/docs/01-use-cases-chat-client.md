# Casos de uso – Lab 01: AI Chat básico (Spring AI)

Este documento recoge **casos de uso reales** donde la técnica introducida
en este laboratorio —un chat AI básico sin memoria ni RAG— es una solución
adecuada dentro de un backend Spring en un contexto de producción.

El foco está en **entender cuándo este enfoque es suficiente** y cuáles son
sus límites, antes de introducir arquitecturas más complejas.

---

## Caso de uso 1 – Asistente técnico interno

### Contexto
Un equipo de desarrollo necesita una forma rápida de consultar
conceptos técnicos generales sin salir del entorno interno de la empresa.

### Problema a resolver
Reducir el tiempo dedicado a búsquedas repetitivas en documentación externa
o fuentes no controladas.

### Rol de la IA
- Responder preguntas técnicas generales
- No acceder a datos internos ni sensibles
- No tomar decisiones automáticas

### Por qué este lab es suficiente
- No se requiere memoria de conversación
- No se necesita acceso a documentación privada
- El backend controla completamente el input y el output del modelo

### Riesgos y límites
- Las respuestas no están adaptadas al dominio específico de la empresa
- No hay trazabilidad ni referencias a fuentes
- No sustituye documentación oficial ni conocimiento experto

---

## Caso de uso 2 – Endpoint de ayuda contextual

### Contexto
Una aplicación web ofrece ayuda contextual al usuario en determinados
formularios o pantallas.

### Problema a resolver
Explicar conceptos generales sin saturar la interfaz con texto estático
o documentación extensa.

### Rol de la IA
- Generar explicaciones breves y genéricas
- No personalizar respuestas por usuario
- No mantener contexto entre peticiones

### Por qué este lab es suficiente
- El prompt se construye dinámicamente desde el backend
- Cada petición es independiente y controlada
- No es necesario gestionar estado conversacional

### Riesgos y límites
- Respuestas no deterministas
- Requiere validación de contenido si se expone a usuarios finales
- No adecuada para flujos críticos o regulatorios

---

## Caso de uso 3 – Prototipado rápido de funcionalidades con IA

### Contexto
Un equipo quiere validar si un caso de uso con IA aporta valor real
antes de invertir en una arquitectura más compleja.

### Problema a resolver
Evaluar impacto funcional y experiencia de usuario sin sobreingeniería.

### Rol de la IA
- Generar respuestas simples
- Servir como prueba de concepto controlada

### Por qué este lab es suficiente
- Integración mínima
- Bajo coste de implementación
- Fácil de descartar o evolucionar hacia soluciones más avanzadas

### Riesgos y límites
- No apto para producción sin capas adicionales
- No escalable sin control de contexto, coste y observabilidad

---

## Conclusión

Este laboratorio demuestra cómo integrar un modelo de lenguaje en un backend
Spring de forma **controlada, explícita y desacoplada**, entendiendo que la IA
es una **dependencia externa**, no el núcleo del sistema.

Los siguientes laboratorios amplían esta base incorporando memoria,
recuperación de información (RAG) y herramientas, siempre manteniendo
el backend como autoridad del flujo.

