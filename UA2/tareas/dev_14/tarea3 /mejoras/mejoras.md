# Revisión de la Tarea 3
## Reflexión
- ¿Qué errores encontré en mi código?
- En la ejecución concurrente, no verificaba correctamente que todos los hilos finalizaran antes de capturar el tiempo total, lo que podía dar tiempos inconsistentes.
- ¿Qué aprendí al corregirlo?
- A sincronizar correctamente la ejecución concurrente usando join() y entender cómo afecta al rendimiento.
- ¿Cómo me ayudaron los tests unitarios?
- os tests unitarios me permitieron validar que el método contarCaracteres manejaba adecuadamente casos como:
Archivos vacíos.
Archivos con caracteres especiales.
Archivos inexistentes.
## Tests Implementados
- Caso de prueba 1: Descripción y resultado.Lectura de archivo válido
- Resultado: Test superado. El método cuenta correctamente los caracteres y los reporta en consola.
-Caso de prueba 2: Descripción y resultado.Lectura de archivo inexistente
-Resultado: Test superado. Se imprime el mensaje de error adecuado en consola
-Caso de prueba 3: Descripción y resultado.Lectura de archivo vacio
-Resultado: Test superado. El método indica que el total de caracteres es 0.
## Observaciones
- Observaciones generales sobre el ejercicio.
- Las pruebas fueron útiles para identificar casos límite, como archivos vacíos o inexistentes, que inicialmente no había contemplado en el código.
- La implementación concurrente mostró mejoras significativas en el rendimiento cuando se probaron archivos más grandes pero en archivos pequeños no hay diferencia
