# Revisión de la Tarea 1
## Reflexión

- ¿Qué errores encontré en mi código?

    Primera versión (ua2tarea1fr1):

    No se verifica si el resultado es correcto tras la ejecución.

    Segunda versión (ua2tarea1fr2):

    Aunque el uso de synchronized en el acceso al contador resuelve los problemas de concurrencia, sincronizar en la clase puede ser menos eficiente en escenarios de alta carga.

    Tercera versión (ua2tarea1fr2runnable):

    Al usar Runnable, el diseño es más limpio, pero el comportamiento es similar a la segunda versión. Sin embargo, sincronizar en la clase sigue siendo un cuello de botella.

- ¿Qué aprendí al corregirlo?

    La importancia de la sincronización en programas concurrentes.
    Cómo pequeños cambios en el manejo de concurrencia afectan el rendimiento y la consistencia.
    La diferencia práctica entre el uso de Thread y Runnable para modelar tareas.

- ¿Cómo me ayudaron los tests unitarios?

    Validaron que las tres versiones producen el resultado esperado cuando la sincronización está bien implementada.
    Permitieron comparar el tiempo de ejecución entre las tres implementaciones.

## Tests Implementados

- Caso de prueba 1: Descripción y resultado.

    Primera versión (ua2tarea1fr1):
    Descripción: Comprueba que el valor final del contador es 5000 después de que todos los hilos terminen.
    Resultado esperado: No lo hace a veces ya que no están sincronizados.

Segunda versión (ua2tarea1fr2):

    Descripción: Verifica que la versión sin sincronización (ua2tarea1fr1) produce resultados inconsistentes.
    Resultado esperado: El valor del contador es menor a 5000 en la mayoría de las ejecuciones de la versión sin sincronización.

Tercera versión (ua2tarea1fr2runnable):

    Descripción: Compara el tiempo de ejecución entre las versiones sincronizadas.
    Resultado esperado: La versión con Runnable debe ser ligeramente más eficiente en tiempo.

## Observaciones
- Observaciones generales sobre el ejercicio.

Primera versión (ua2tarea1fr1):

    Es un excelente ejemplo para demostrar las condiciones de carrera en entornos concurrentes.
    Sirve como base para explicar la importancia de la sincronización.

Segunda versión (ua2tarea1fr2):

    La sincronización con synchronized en la clase asegura consistencia pero puede reducir la eficiencia en escenarios de alta carga.

Tercera versión (ua2tarea1fr2runnable):

    Implementación más modular al usar Runnable, lo que promueve un diseño más limpio y reutilizable.
    Muestra una alternativa más eficiente, aunque con resultados equivalentes a la segunda versión.