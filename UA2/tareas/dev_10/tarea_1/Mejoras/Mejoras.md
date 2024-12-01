# 📝 Revisión de la Tarea 1

## 📌 Reflexión
- **¿Qué errores encontré en mi código?**  
  Al principio, no había implementado correctamente la sincronización de los hilos, lo que causaba condiciones de carrera. Como resultado, los hilos no compartían el recurso de manera segura, lo que provocaba que el contador no tuviera el valor esperado después de la ejecución de ambos hilos.

- **¿Qué aprendí al corregirlo?**  
  Aprendí la importancia de usar `synchronized` para evitar condiciones de carrera cuando varios hilos acceden a recursos compartidos. La sincronización garantiza que solo un hilo pueda acceder a la sección crítica del código a la vez, lo que permite un resultado consistente.

- **¿Cómo me ayudaron los tests unitarios?**  
  Los tests unitarios fueron clave para identificar los problemas de sincronización. Al verificar los resultados de las operaciones con múltiples hilos, los tests me permitieron asegurarme de que el contador siempre se incrementara de forma correcta, tanto con la sincronización activada como desactivada.

---

## ✅ Tests Implementados
1. **Caso de prueba 1:**  
   Se verifica que el contador se incremente correctamente cuando dos hilos sincronizados realizan la operación de incremento.
    - Resultado esperado: `2000` (El contador debe llegar a 2000 después de que ambos hilos sumen 1000 cada uno.)
    - Resultado obtenido: `2000` (El test pasó correctamente.)

2. **Caso de prueba 2:**  
   Se verifica si el contador no alcanza el valor esperado debido a condiciones de carrera cuando los hilos no están sincronizados.
    - Resultado esperado: `2000` (El contador debería llegar a 2000 con sincronización, pero con las condiciones de carrera no será consistente.)
    - Resultado obtenido: El valor fue menor a 2000, lo que indica que las condiciones de carrera afectan al resultado.

---

## 💡 Observaciones
- **Observaciones generales:**  
  Aunque la sincronización solucionó el problema de las condiciones de carrera, es importante asegurarse de que la sincronización no genere cuellos de botella o afecte el rendimiento si se manejan muchos hilos simultáneamente. Además, el uso de `synchronized` fue adecuado en este caso, pero en situaciones más complejas podríamos explorar otras opciones como los `Locks`.

---

> **Nota:**  
> Este documento fue creado para reflexionar sobre la calidad del trabajo realizado y fomentar la mejora continua. ✨
