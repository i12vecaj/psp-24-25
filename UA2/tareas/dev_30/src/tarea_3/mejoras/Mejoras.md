# Revisión de la Tarea 3

## 📌 Reflexión

### ¿Qué errores encontré en mi código?

1. La clase `Consumidor` heredaba de `Thread`, lo que limitaba la reutilización del código. Esto se solucionó al implementar `Runnable`.
2. El manejo de excepciones no era adecuado, ya que simplemente lanzaba excepciones sin proporcionar un mensaje claro.
3. El proceso de lectura de archivos no estaba optimizado y no se manejaba adecuadamente la captura de errores de entrada/salida.

### ¿Qué aprendí al corregirlo?

- Aprendí a implemetar `Runnable` para mejora la flexibilidad y reutilización del código, permitiendo que el hilo sea más manejable y reutilizable.
- También aprendí la importancia de un manejo adecuado de excepciones, especialmente cuando se trabajan con operaciones de E/S, para garantizar que el programa no se detenga por errores inesperados.

### ¿Cómo me ayudaron los tests unitarios?

- Los tests unitarios me ayudaron a asegurar que la lógica de procesamiento de archivos funcionara correctamente, incluso en escenarios en los que los archivos no existen o no se pueden leer.
---

## ✅ Tests Implementados

### Caso de prueba 1: Contador de caracteres

**Descripción:**  
Verificar que el contador de caracteres funciona correctamente para un archivo válido.

**Resultado esperado:**  
El número de caracteres del archivo debe ser el esperado.

**Resultado obtenido:**  
✔️ Test pasado con éxito.

---

### Caso de prueba 2: Manejo de archivo no encontrado

**Descripción:**  
Verificar que el sistema maneje correctamente los errores cuando el archivo no existe.

**Resultado esperado:**  
Un mensaje de error debe ser impreso en la salida de error.

**Resultado obtenido:**  
✔️ Test pasado con éxito.

---

### Caso de prueba 3: Manejo de excepciones en la lectura del archivo

**Descripción:**  
Verificar que el sistema maneje correctamente los errores de lectura.

**Resultado esperado:**  
Un mensaje de error debe ser impreso en la salida de error.

**Resultado obtenido:**  
✔️ Test pasado con éxito.

---

## 💡 Observaciones

- El uso de `Runnable` en lugar de `Thread` mejora la flexibilidad del código y facilita su reutilización.
- Es fundamental manejar las excepciones adecuadamente para evitar que el programa termine inesperadamente.
- Los tests son esenciales para garantizar que los diferentes escenarios de entrada y salida se gestionen correctamente.
