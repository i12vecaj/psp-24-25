# Revisión de la Tarea 1
## Reflexión ua2tarea1fr1
- ¿Qué errores encontré en mi código?
 - Uso de una variable compartida (contador) sin sincronización, lo que provoca condiciones de carrera.

 - Resultado final del contador puede ser incorrecto debido al acceso concurrente no controlado.

- ¿Qué aprendí al corregirlo?

  -Sin sincronización, los hilos pueden sobrescribir valores entre sí.

- ¿Cómo me ayudaron los tests unitarios?
  Identificaron que el valor esperado del contador no siempre coincide con el resultado real debido a condiciones de carrera.
## Tests Implementados
- Caso de prueba 1: añadir un  System.out.println("soy el hilox: " + contador); para comprobar que orden seguian, no se observo ningun orden y aunque la mayorias de veces obtenian el resultado final, a veces no, pero era bastante raro ante la simpleza que se pedia a los hilos
  
## Observaciones
-La falta de sincronización provoca resultados inconsistentes.
-Este enfoque ilustra cómo el acceso no sincronizado puede causar problemas en aplicaciones multihilo.


## Reflexión ua2tarea1fr2
- ¿Qué errores encontré en mi código?
- Sin errores funcionales. La sincronización asegura un comportamiento consistente.
- ¿Qué aprendí ?
- El uso de synchronized garantiza que solo un hilo pueda ejecutar la sección crítica a la vez.
- ¿Cómo me ayudaron los tests unitarios?
- Verificaron que el valor del contador es correcto (5000) al utilizar sincronización.
## Tests Implementados
- Caso de prueba 1: se repitio lo mismo que en el codigo anterior para comprobar el orden
## Observaciones
- La sincronización mejora significativamente la precisión y consistencia del programa.

## Reflexión ua2tarea1fr2runnable
- ¿Qué errores encontré en mi código?
- Sin errores funcionales.
- ¿Qué aprendí ?
- La sincronización dentro de la clase Incrementador asegura la consistencia.
- ¿Cómo me ayudaron los tests unitarios?
- Confirmaron que el valor del contador es correcto (5000) y el diseño modular funciona como se espera.
## Tests Implementados
- Caso de prueba 1: se repitio lo mismo que en el codigo anterior para comprobar el orden
## Observaciones
- Este enfoque es más modular y sigue mejores prácticas de diseño
