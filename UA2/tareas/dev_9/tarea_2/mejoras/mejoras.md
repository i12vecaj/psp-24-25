# Revisión de la Tarea: Clase CuentaCorriente y Comprador 🏦

## **Reflexión** 💭

- **¿Qué errores encontré en mi código?** ❌

  1. No había contemplado, que ocurriría si dos hilos intentan acceder simultáneamente al método `anadirSaldo` sin sincronización.

- **¿Qué aprendí al corregirlo?** 🌟

  - He aprendido a implementar métodos sincronizados para manejar condiciones de carrera entre hilos.
  - He aprendido a trabajar con hilos sincronizados.

- **¿Cómo me ayudaron los tests unitarios?** 🧪
  - Detectaron errores en la inicialización del saldo y confirmaron que los métodos sincronizados funcionaban correctamente.
  - Me permitieron verificar que los hilos trabajan como se esperaba, asignando y sumando saldos de manera concurrente sin perder datos.
  - Aseguraron que la cuenta regresara el saldo correcto en escenarios básicos y concurrentes.

---

## **Tests Implementados** ✅

- **Caso de prueba 1: Asignación correcta de saldo desde un hilo**

  - **Descripción:** Un hilo único añade un saldo inicial a la cuenta. Se verifica que el saldo resultante sea igual al esperado.
  - **Resultado:** Pasó exitosamente, confirmando que el saldo se asigna correctamente desde un hilo.

- **Caso de prueba 2: Saldo inicial en cero**

  - **Descripción:** Se crea una instancia de `CuentaCorriente` y se verifica que el saldo inicial sea 0.0f.
  - **Resultado:** Pasó exitosamente, garantizando que el saldo inicial está correctamente configurado.

- **Caso de prueba 3: Acceso concurrente de múltiples hilos**
  - **Descripción:** Varios hilos (instancias de `Comprador`) intentan añadir saldo a la cuenta simultáneamente. Se verifica que el saldo final sea la suma correcta de todos los depósitos realizados.
  - **Resultado:** Pasó exitosamente, asegurando que el método sincronizado evita condiciones de carrera y mantiene la consistencia del saldo.

---

## **Observaciones** 👀

- **Observaciones generales sobre el ejercicio:**
  - El diseño inicial funcionaba, pero no estaba totalmente preparado para manejar acceso concurrente, lo que complicaba el control de las condiciones de carrera.

---
