# 📝 Revisión de la Tarea 2

## 📌 Reflexión

### ¿Qué errores encontré en mi código?

El problema principal del código original era que utilizaba hilos (`Thread`) de manera básica para modificar el saldo compartido de una cuenta. Esto podía provocar problemas como condiciones de carrera. Además, la sincronización no era lo suficientemente robusta, y no había pruebas unitarias para comprobar si todo funcionaba correctamente.

Aunque se usaba la palabra clave `synchronized` en el método `addSaldo`, el manejo de excepciones no era el mejor. Esto dejaba al programa vulnerable a posibles errores que podían interrumpir su funcionamiento.

---

### ¿Qué aprendí al corregirlo?

Corregir este código me enseñó lo crucial que es usar sincronización al trabajar con hilos para evitar problemas como condiciones de carrera. También aprendí que es vital manejar las excepciones correctamente para que el programa no falle de forma inesperada.

Además, descubrí que usar `Runnable` en lugar de `Thread` permite un diseño más flexible y reutilizable del código. También aprendí la importancia de las pruebas unitarias para garantizar que los hilos y la sincronización están funcionando como deberían.

---

### ¿Cómo me ayudaron los tests unitarios?

Los tests unitarios fueron clave para verificar que el saldo final se sumaba correctamente, incluso cuando varios hilos trabajaban de forma simultánea. Aseguraron que el programa manejara bien la concurrencia y que los valores fueran consistentes.

Además, los tests me permitieron comprobar que las excepciones se manejaban correctamente y que el código respondía bien en diferentes situaciones, incluso en escenarios inesperados.

---

## ✅ Tests Implementados

### **Caso 1: Adición de saldo concurrente**
**Descripción:** Verificar que varios hilos puedan agregar saldo al mismo tiempo y que el saldo final sea correcto.  
**Resultado esperado:** La suma de los saldos debe ser 3800 (2000 + 500 + 600 + 700).  
**Resultado obtenido:** ✔️ Test pasado con éxito.

---

### **Caso 2: Verificación de sincronización y orden**
**Descripción:** Comprobar que los hilos trabajan de forma sincronizada y que el saldo se actualiza correctamente sin interferencias.  
**Resultado esperado:** El saldo final debe ser 3800, y el orden de los ingresos debe ser consistente.  
**Resultado obtenido:** ✔️ Test pasado con éxito.

---

### **Caso 3: Manejo de excepciones**
**Descripción:** Asegurarse de que cualquier error en los hilos se maneje de forma adecuada sin que el programa se detenga.  
**Resultado esperado:** No deben ocurrir errores no controlados, y las excepciones deben ser gestionadas correctamente.  
**Resultado obtenido:** ✔️ Test pasado con éxito.

---

## 💡 Observaciones

### Observaciones generales:

- Usar la palabra clave `synchronized` en el método `addSaldo` fue esencial para evitar que los hilos interfirieran entre sí al modificar el saldo. Esto garantiza que las operaciones concurrentes sean seguras y los datos se mantengan consistentes.

- El manejo de excepciones se mejoró considerablemente, lo que hace que el programa sea más estable incluso en casos de error.

- Implementar pruebas unitarias fue fundamental para validar que el programa funciona correctamente en situaciones reales y extremas. Estas pruebas aseguraron que el saldo final fuera el esperado y que no ocurrieran errores inesperados.  
