## Reflexión Sincronizada

- ¿Qué errores encontré en mi código?
- sin errores
- ¿Qué aprendí?
- Es útil para evitar errores cuando se manipulan datos compartidos en un entorno multihilo.
- ¿Cómo me ayudaron los tests unitarios?
- Verificaron que el saldo final es consistente y corresponde a la suma de todos los incrementos.
## Tests Implementados
- Caso de prueba 1: Descripción y resultado.uso del test de comprobacion:
- Test sincronizado: El saldo final será correcto (350 para 5 usuarios con incrementos de 50).

## Observaciones
- Aunque correcto, el uso de synchronized puede impactar el rendimiento si hay un alto número de hilos debido a la contención.

  ## Reflexión No Sincronizada

- ¿Qué errores encontré en mi código?
- Condiciones de carrera: varios hilos leen y escriben en saldo sin un orden garantizado.
- ¿Qué aprendí?
- Sin sincronización, el acceso concurrente a variables compartidas puede causar errores impredecibles.
- ¿Cómo me ayudaron los tests unitarios?
-Identificaron discrepancias en el saldo final debido a las condiciones de carrera.
## Tests Implementados
- Caso de prueba 1: Descripción y resultado.uso del test de comprobacion:
-El saldo final no será predecible y puede diferir de 350 debido a condiciones de carrera.
- Caso de prueba 1: Descripción y resultado.Reducir el tiempo de espera:
- al ser el trabajo pedido tan simple para los hilos y que al tener un tiempo de espera indirectamente les podemos una especie de orden por tiempo , al bajar el tiempo si se producen errores de carrera porque los hilos adquieren el dato de otro hilo sin este actualizarse, obteniendo el dato anterior y produciendo un resultado inconsistente

## Observaciones
-Es evidente que la falta de sincronización provoca resultados erróneos e impredecibles, especialmente al aumentar el número de hilos y reducir el tiempo
