¿Qué errores encontré en mi código?  

No se manejaban correctamente las prioridades de los hilos.
No se sincronizaba el acceso a la variable compartida saldo en la clase CuentaCorriente.

¿Qué aprendí al corregirlo?  

La importancia de sincronizar el acceso a recursos compartidos en un entorno multihilo.
Cómo utilizar el método join para asegurar que todos los hilos terminen antes de continuar con la ejecución del programa principal.

Test en Operaciones

Descripción: Verificar que el saldo final de la cuenta corriente es correcto después de ejecutar múltiples hilos que realizan ingresos.
Resultado: El saldo final es el esperado, lo que indica que la sincronización y la lógica de negocio funcionan correctamente.