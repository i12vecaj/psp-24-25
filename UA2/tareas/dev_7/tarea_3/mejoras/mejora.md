# Revisión de la Tarea: Contar caracteres en archivos

## Reflexión

- **¿Qué errores encontré en mi código?**  
  Había errores en la gestión de excepciones cuando un archivo no existía.

- **¿Qué aprendí al corregirlo?**  
  Aprendí la importancia de manejar adecuadamente las excepciones al interactuar con archivos. Además, entendí la utilidad de los hilos para procesar múltiples tareas en paralelo.

- **¿Cómo me ayudaron los tests unitarios?**  
  Los tests aseguraron que el programa se comportara de manera consistente en diferentes escenarios, como archivos inexistentes, archivos grandes y múltiples hilos.
---
## Tests Implementados

1. **Caso de prueba 1: Leer archivo existente**  
   - **Descripción:** Comprueba que el método `caracteresFichero` cuenta correctamente los caracteres de un archivo existente.  
   - **Resultado:** Test exitoso; los caracteres se cuentan correctamente.

2. **Caso de prueba 2: Manejo de archivo inexistente**  
   - **Descripción:** Verifica que el programa maneje correctamente un archivo inexistente sin fallar.  
   - **Resultado:** Test exitoso; las excepciones se manejaron correctamente.

3. **Caso de prueba 3: Ejecución de hilo para leer archivo**  
   - **Descripción:** Comprueba que un hilo pueda contar caracteres correctamente en un archivo.  
   - **Resultado:** Test exitoso; el hilo cuenta los caracteres correctamente.

4. **Caso de prueba 4: Procesamiento concurrente con un archivo grande**  
   - **Descripción:** Verifica el manejo eficiente de un archivo grande usando un hilo.  
   - **Resultado:** Test exitoso; el hilo procesa el archivo correctamente sin errores ni problemas de memoria.

---
## Observaciones

- La implementación de la clase `Hilo` simplificó la ejecución en paralelo.
- Los tests ayudaron a saber la importancia de sincronizar correctamente la salida del programa al usar múltiples hilos para evitar mensajes desordenados.

---