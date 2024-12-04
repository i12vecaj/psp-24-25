# 📝 Revisión de la Tarea X

## 📌 Reflexión
- *¿Qué errores encontré en mi código?*  
  Se me presentaron los siguientes problemas: sincronización de los métodos `getSaldo()`, `setSaldo()`, y `agregarSaldo()` podría interferir con los hilos en ejecución. 

- *¿Qué aprendí al corregirlo?*  
  Aprendí que la sincronización es fundamental cuando se trabaja con múltiples hilos para evitar condiciones de carrera. Al utilizar `synchronized`, se asegura que solo un hilo pueda acceder a los métodos críticos de la clase `CuentaCorriente` en un momento dado, lo que evita la corrupción del saldo.

- *¿Cómo me ayudaron los tests unitarios?*  
  Los tests me ayudaron a identificar problemas de concurrencia y sincronización al verificar cómo interactúan los hilos con la clase `CuentaCorriente`. También me permitieron asegurar que los resultados estuvieran dentro de los límites esperados y que los hilos se ejecutaran correctamente sin afectar la consistencia de los datos.

---

## ✅ Tests Implementados
1. *Caso de prueba 1:*  
   Verifica el saldo inicial de la cuenta corriente.
    - Resultado esperado: 100
    - Resultado obtenido: 100

2. *Caso de prueba 2:*  
   Verifica el comportamiento de `setSaldo()` al modificar el saldo.
    - Resultado esperado: 200
    - Resultado obtenido: 200

3. *Caso de prueba 3:*  
   Verifica que el saldo se actualice correctamente cuando se agregue dinero.
    - Resultado esperado: 150 y luego 250
    - Resultado obtenido: 150 y luego 250

4. *Caso de prueba 4:*  
   Verifica que el saldo no sea inconsistente después de ejecutar múltiples hilos.
    - Resultado esperado: Un saldo entre 250 y 300.
    - Resultado obtenido: Un saldo dentro del rango esperado.

5. *Caso de prueba 5:*  
   Verifica que los hilos con diferentes prioridades no interfieran en la actualización del saldo.
    - Resultado esperado: Un saldo entre 350 y 400.
    - Resultado obtenido: Un saldo dentro del rango esperado.

---

## 💡 Observaciones
- *Observaciones generales:*  
  Los tests unitarios fueron muy útiles para detectar posibles problemas de concurrencia. Gracias a ellos, pude asegurarme de que los hilos estaban siendo gestionados correctamente sin alterar el saldo de la cuenta de manera inesperada.

---
