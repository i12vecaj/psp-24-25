# Revisión de la Tarea 2 Tema 2
## Reflexión
- ¿Qué errores encontré en mi código?
    Condición no sincronizada: Antes de implementar synchronized, los métodos getSaldo() y setSaldo() no estaban sincronizados, lo que provocaba inconsistencias en el saldo cuando múltiples hilos accedían al mismo tiempo.

    Incremento no atómico: El incremento del saldo dentro de un bucle sin sincronización provocaba condiciones de carrera.

    Uso del método Thread.sleep: No se manejaron correctamente las posibles excepciones de interrupción al principio.

- ¿Qué aprendí al corregirlo?
    La importancia de sincronizar métodos que son accesibles desde múltiples hilos para evitar condiciones de carrera y garantizar la consistencia de los datos compartidos.

    Cómo implementar synchronized en métodos o bloques para que los hilos accedan de forma ordenada.

    El impacto de los tiempos de espera (Thread.sleep) y cómo simular retrasos en operaciones para probar condiciones de concurrencia.

- ¿Cómo me ayudaron los tests unitarios?
    Detectaron rápidamente inconsistencias en el saldo cuando los hilos no estaban sincronizados.

    Probaron diferentes escenarios de concurrencia y validaron que el saldo final era correcto después de corregir los problemas.

## Tests Implementados
- Caso de prueba 1: Descripción y resultado.
    Descripción: Tres hilos incrementan el saldo de manera concurrente con diferentes cantidades. El test verifica que el saldo final es correcto.

    Resultado esperado: La suma de todas las cantidades debe coincidir con el saldo final.
## Observaciones
- Observaciones generales sobre el ejercicio.
    Uso de synchronized: Es imprescindible en escenarios multithreading para garantizar la seguridad de los datos compartidos.

    Eficiencia: El uso de un bucle para incrementar el saldo en pequeños pasos (uno por uno) puede ser innecesario. Una alternativa sería incrementar directamente con saldo += cantidad.

    Simulación del retraso: La función dormir es útil para simular condiciones reales, pero en un entorno de producción, se debe manejar cuidadosamente el impacto de las pausas artificiales.

    Buena práctica: La estructura modular del código facilita la implementación de tests unitarios y la comprensión de los problemas de concurrencia.




