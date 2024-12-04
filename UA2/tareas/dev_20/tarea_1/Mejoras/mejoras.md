# 📝 Revisión de la Tarea 1

## 📌 Reflexión
- *¿Qué errores encontré en mi código?*  
  La IA me presentó cuatro problemas principales en mi código:
    1. El uso de variables estáticas compartidas sin sincronización adecuada, lo que podría causar condiciones de carrera y resultados incorrectos cuando múltiples hilos acceden a ellas simultáneamente.
    2. El uso de `Thread` en lugar de `Runnable` para la ejecución de los hilos. Al extender de `Thread`, se pierde flexibilidad en el código, ya que `Runnable` permite separar la lógica de la ejecución del hilo, facilitando la reutilización.
    3. Un manejo de excepciones poco claro, ya que simplemente se lanzaba una `RuntimeException` al ocurrir un `InterruptedException`, lo que podría resultar en una pérdida de información y confusión en la ejecución.
    4. La legibilidad del código, ya que en lugar de usar prácticas más concisas como `forEach` y mejor estructuración, el código original podía resultar más difícil de leer y mantener.

- *¿Qué aprendí al corregirlo?*  
  Aprendí que el uso de `AtomicInteger` me ayuda a prevenir condiciones de carrera y que es necesario que las excepciones se administren también con `Thread.currentThread().interrupt();`

- *¿Cómo me ayudaron los tests unitarios?*  
  Los tests unitarios me ayudaron a verificar que las modificaciones realizadas realmente resuelven los problemas de concurrencia y manejo de excepciones.

---

## ✅ Tests Implementados
1. *Caso de prueba 1:*  
   Prueba para verificar que el contador se incrementa correctamente después de ejecutar los 5 hilos, cada uno incrementando el valor en 1000.
    - Resultado esperado: El valor final del contador debe ser 5000 (1000 * 5).
    - Resultado obtenido: El valor final fue 5000, lo que confirma que los hilos están incrementando correctamente el valor de la variable de manera atómica.

2. *Caso de prueba 2:*  
   Prueba para asegurar que las excepciones `InterruptedException` son manejadas correctamente sin afectar la ejecución de los hilos.
    - Resultado esperado: El hilo debe continuar su ejecución después de manejar la excepción correctamente y mostrar un mensaje de error adecuado si ocurre una interrupción.
    - Resultado obtenido: El manejo de excepciones fue exitoso y los hilos continuaron ejecutándose correctamente después de un `InterruptedException`. Se imprimió el mensaje de error correspondiente.

---

## 💡 Observaciones
- *Observaciones generales:*  
  Los cambios realizados mejoraron considerablemente la escalabilidad y la legibilidad del código.