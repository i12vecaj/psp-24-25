¿Qué errores encontré en mi código?  

No estaba manejando correctamente las excepciones en la ejecución concurrente.
No estaba utilizando join() para esperar a que todos los hilos terminen antes de medir el tiempo total de ejecución concurrente.

¿Qué aprendí al corregirlo?  

La importancia de manejar adecuadamente las excepciones en un entorno multihilo.
Cómo utilizar join() para asegurar que todos los hilos hayan terminado antes de continuar con la ejecución del programa.

Test 

No se implementar ningún código que teste el ejercicio, asi que he comprobado que realmente cuente el numero de caracteres poniendo en el documento1 una palabra "sol" que tiene 3 caracteres asi lo he testeado