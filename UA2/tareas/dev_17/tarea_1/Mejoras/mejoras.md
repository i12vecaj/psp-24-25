# Revisión de la Tarea 1 Tema 2
## Reflexión
- ¿Qué errores encontré en mi código?
    Sincronización en ua2tarea1fr1:

    La primera versión no implementa sincronización en el método incrementarValor. Esto genera condiciones de carrera, ya que múltiples hilos pueden acceder y modificar la variable valor_contador simultáneamente, provocando resultados incorrectos.

    Duplicidad del código:

    Aunque las tres clases (ua2tarea1fr1, ua2tarea1fr2) tienen funcionalidades similares, la primera no asegura la integridad de los datos por la ausencia de synchronized.

    Simulación de retraso:

    El uso de Thread.sleep(1000) ralentiza la ejecución, pero es útil para demostrar la concurrencia y el impacto de la sincronización. Sin embargo, en un entorno real, esto puede ser poco eficiente.

- ¿Qué aprendí al corregirlo?
    La importancia de synchronized para evitar condiciones de carrera en métodos que modifican variables compartidas.

    Cómo pequeñas diferencias (presencia o ausencia de synchronized) pueden causar comportamientos inesperados en aplicaciones concurrentes.

    La estructura modular del código facilita el análisis y la implementación de mejoras progresivas.

- ¿Cómo me ayudaron los tests unitarios?
    Identificaron que en ua2tarea1fr1 el valor del contador al final no era consistente con la suma esperada (5000), mientras que en ua2tarea1fr2 sí se mantenía correcto.

    Probaron escenarios concurrentes para confirmar que la sincronización resolvía el problema.

## Tests Implementados
- Caso de prueba 1: Incremento concurrente sin sincronización
    Descripción: Probar el incremento del contador sin synchronized y verificar si el resultado es inconsistente.

    Resultado esperado: El valor final del contador puede ser incorrecto debido a condiciones de carrera.

- Caso de prueba 2: Incremento concurrente con sincronización
    Descripción: Probar el incremento del contador con synchronized y verificar si el resultado es correcto.

    Resultado esperado: El valor final del contador debe ser igual a la suma de los incrementos.

## Observaciones
- Observaciones generales sobre el ejercicio.
    Sincronización:

    ua2tarea1fr1 no garantiza consistencia en el valor del contador, mientras que ua2tarea1fr2 sí lo hace gracias al uso de synchronized.

    Esto resalta la importancia de proteger los datos compartidos en aplicaciones concurrentes.

    Duplicación del código:
    Aunque las clases son funcionalmente idénticas, las diferencias principales están en la implementación de sincronización. Consolidar el código en una única clase reutilizable mejoraría la mantenibilidad.

    Impacto de la concurrencia:
    Probar con y sin sincronización ayuda a comprender cómo funcionan las condiciones de carrera y cómo resolverlas.

    Eficiencia:
    El uso de Thread.sleep ralentiza innecesariamente las pruebas y podría eliminarse en versiones más optimizadas.
