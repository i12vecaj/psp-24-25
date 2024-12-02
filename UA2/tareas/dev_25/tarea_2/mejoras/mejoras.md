# Revisión de la Tarea 2
## Reflexión
- ¿Qué errores encontré en mi código?

    La variable cantidad en la clase HiloIncrementar no se utiliza directamente en la operación de incremento, lo que puede causar confusión al lector del código.
    Aunque los métodos de la clase CuentaCorriente están sincronizados, existe un potencial cuello de botella si múltiples hilos intentan acceder a métodos como getSaldo o setSaldo simultáneamente mientras otro incrementa el saldo.
    El método incrementar incluye un bucle de 1000 iteraciones para incrementar el saldo, lo cual no es dinámico ni flexible. Además, incrementa el saldo directamente en lugar de usar un parámetro para definir cuánto se debe incrementar.
Mensajes de salida redundantes:

    Imprimir el saldo en cada iteración puede sobrecargar la salida, especialmente cuando múltiples hilos están trabajando simultáneamente.

- ¿Qué aprendí al corregirlo?

    A optimizar el código evitando operaciones innecesarias y redundantes.
    A utilizar tests unitarios para verificar la funcionalidad antes de implementar el programa completo.
    A identificar los puntos críticos en un programa multihilo como la sincronización.

- ¿Cómo me ayudaron los tests unitarios?
    Validar que el saldo inicial y final sean consistentes tras los incrementos.
    Detectar errores lógicos en las operaciones de sincronización y concurrencia.
    Comprobar que los incrementos y mensajes se generen de manera coherente y precisa.

## Tests Implementados

- Caso de prueba 1: Descripción y resultado.

    Descripción: Verifica que el saldo inicial de la cuenta se incremente correctamente después de ejecutar un hilo.
    Resultado esperado: El saldo final debe ser igual al saldo inicial más el incremento realizado por el hilo.

## Observaciones

- Observaciones generales sobre el ejercicio.

    Aunque el código asegura sincronización en métodos críticos, el diseño puede mejorar usando bloques sincronizados más específicos.
    Es mejor reducir la cantidad de salidas impresas, especialmente en entornos multihilo, para evitar desorden en la consola.
    Cambiar el método incrementar para que permita incrementos personalizados según los parámetros mejora la reutilización y legibilidad.