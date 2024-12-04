# 📝 Revisión de la Tarea 1

## 📌 Reflexión

### ¿Qué errores encontré en mi código?
- En la versión original, usé `Thread` para crear hilos. Aunque es válido, no es tan flexible como implementar `Runnable`, que permite compartir mejor la lógica de los hilos y es más reutilizable.
### ¿Qué aprendí al corregirlo?
- Implementar `Runnable` es más práctico, ya que separa la lógica del hilo de su ejecución, lo que mejora la flexibilidad y el diseño del código.
- Manejar correctamente las excepciones hace que el programa sea más robusto y fácil de depurar.

### ¿Cómo me ayudaron los tests unitarios?
- Aprendí que implementar  `Runnable` no afectara el comportamiento del programa.
- Aseguraron que el contador alcanzara el valor correcto, confirmando que no había errores de concurrencia.

---

## ✅ Tests Implementados

### Incremento básico del contador
- **Qué hice:** Probé que el contador funcionara correctamente al usar `Runnable` con varios hilos.
- **Qué esperaba:** Que el valor final del contador fuera `5000` (1000 incrementos por cada uno de los 5 hilos).
- **Resultado:** Funcionó perfectamente.



---

## 💡 Observaciones

- **Sobre `Runnable`:** Usarlo en lugar de heredar de `Thread` hace el diseño más flexible y permite reutilizar la lógica del hilo en diferentes contextos, lo cual es muy útil a largo plazo.
- **Manejo de excepciones:** Hacerlo correctamente es esencial para que el programa sea más confiable y fácil de mantener.