# **Revisión de la Tarea: Clase Ficheros** 📝

---

## **Reflexión** 💭

- **¿Qué errores encontré en mi código?** ❌

  1. No había controlado los errores al crear archivos.
  2. La lógica para contar caracteres no era accesible desde los tests porque el atributo `numero_caracteres` era privado y no tenía un método getter.

- **¿Qué aprendí al corregirlo?** 🌟

  - He aprendido a realizar test unitarios.
  - He comprendido la importancia de manejar excepciones adecuadamente.

- **¿Cómo me ayudaron los tests unitarios?** 🧪
  - Detectaron errores que no había previsto, como el manejo incorrecto de archivos no existentes.
  - He podido verificar que los cambios realizados no afectaran la funcionalidad del código existente.
  - Permiten tener un código más claro y estable.

---

## **Tests Implementados** ✅

- **Caso de prueba 1: Archivo válido**

  - **Descripción:**  
    Se creó un archivo con contenido conocido ("Hola Mundo!") y se verificó que el número de caracteres fuera correcto.
  - **Resultado:**  
    ✅ Pasó exitosamente, confirmando que el conteo de caracteres funciona correctamente.

- **Caso de prueba 2: Archivo inexistente**

  - **Descripción:**  
    Se intentó procesar un archivo que no existe y se verificó que el programa maneja correctamente la excepción sin interrumpirse.
  - **Resultado:**  
    ✅ Pasó exitosamente, mostrando el mensaje de error esperado en la salida.

- **Caso de prueba 3: Archivo vacío**
  - **Descripción:**  
    Se probó con un archivo vacío para confirmar que el programa devuelve un conteo de caracteres de 0.
  - **Resultado:**  
    ✅ Pasó exitosamente, confirmando que el programa maneja este caso correctamente.

---

## **Observaciones** 👀

- **Observaciones generales sobre el ejercicio:**
  - El diseño inicial no estaba pensado para pruebas unitarias, lo que ha complicado la creación de los tests.
  - Me gustaría implementar más tests para cubrir otros casos.

---
