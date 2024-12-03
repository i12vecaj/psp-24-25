# Revisión de la Tarea 3

## Reflexión
- **¿Qué errores encontré en mi código?**
  1. No se manejó la excepción que podría ocurrir si un archivo no tiene permisos de lectura.
  2. En el caso concurrente, no hay un manejo explícito de errores para tareas específicas dentro del `ExecutorService`.
  3. El uso de un `FixedThreadPool` con un tamaño igual a la cantidad de archivos no siempre es eficiente si el número de archivos es muy alto.

- **¿Qué aprendí al corregirlo?**
  1. Es importante validar tanto el acceso a los archivos como la existencia de posibles excepciones específicas que puedan surgir en operaciones I/O.
  2. Aprendí cómo usar `Future` para manejar y capturar errores individuales en tareas concurrentes.
  3. Descubrí que optimizar el tamaño del pool de hilos puede mejorar el rendimiento, especialmente en escenarios de cargas altas.

- **¿Cómo me ayudaron los tests unitarios?**
  1. Detectaron casos donde un archivo vacío o inexistente no se manejaba adecuadamente.
  2. Validaron que los resultados concurrentes fueran consistentes con los resultados secuenciales.
  3. Permitieron evaluar el impacto en el rendimiento al ajustar el tamaño del pool de hilos.

## Tests Implementados
- **Caso de prueba 1:** Archivo inexistente.
  - **Descripción:** Se pasa como argumento un archivo que no existe.
  - **Resultado esperado:** Mensaje de error indicando que el archivo no pudo ser leído.
  - **Resultado obtenido:** Comportamiento correcto.
  
- **Caso de prueba 2:** Archivo vacío.
  - **Descripción:** Se pasa como argumento un archivo vacío.
  - **Resultado esperado:** El número de caracteres debe ser 0.
  - **Resultado obtenido:** Comportamiento correcto.

- **Caso de prueba 3:** Comparación entre secuencial y concurrente.
  - **Descripción:** Verificar que los resultados del conteo en ambas ejecuciones sean consistentes.
  - **Resultado esperado:** Mismos valores para el número de caracteres.
  - **Resultado obtenido:** Comportamiento correcto.

- **Caso de prueba 4:** Rendimiento con un número alto de archivos.
  - **Descripción:** Probar el código con una gran cantidad de archivos de diferentes tamaños.
  - **Resultado esperado:** La ejecución concurrente debería ser más rápida que la secuencial.
  - **Resultado obtenido:** Comportamiento correcto.

## Observaciones
- La implementación actual podría optimizar el uso del `FixedThreadPool` para evitar la sobrecarga de hilos innecesarios cuando el número de archivos es elevado.
- Sería útil incluir métricas adicionales, como el número total de caracteres contados en todos los archivos.
- Podría considerarse implementar una interfaz gráfica o un formato de salida más estructurado (como JSON) para mejorar la presentación de los resultados.
