# 📝 Revisión de la Tarea 3: Contador de Caracteres con Hilos

## 📌 Reflexión

- **¿Qué errores encontré en mi código?**  
  Durante la implementación inicial, me encontré con algunos problemas relacionados con el manejo de excepciones y la gestión de recursos. No estaba utilizando adecuadamente el manejo de excepciones para los casos de error al leer archivos inexistentes. También, al principio no estaba utilizando `try-with-resources` para cerrar el `FileReader`, lo que podría haber causado problemas si el archivo no se cerraba correctamente después de su uso.

- **¿Qué aprendí al corregirlo?**  
  Al corregir estos errores, aprendí la importancia de un manejo adecuado de las excepciones y de utilizar la estructura `try-with-resources` para manejar de manera más eficiente los recursos como los `FileReader`. Esto mejora la legibilidad y la fiabilidad del código, evitando posibles fugas de recursos.

- **¿Cómo me ayudaron los tests unitarios?**  
  Los tests unitarios fueron cruciales para garantizar que la funcionalidad de contar caracteres se cumpliera de manera precisa. Al probar casos como la lectura de archivos con diferentes cantidades de caracteres, pude verificar que el código estaba funcionando como se esperaba. Esto ayudó a identificar posibles problemas en el flujo de ejecución, como el manejo incorrecto de excepciones o el cálculo de caracteres.

---

## ✅ Tests Implementados

1. **Caso de prueba 1:**  
   _Prueba con un archivo de texto con contenido conocido_
    - Resultado esperado: _El contador debe devolver el número exacto de caracteres en el archivo._
    - Resultado obtenido: _El contador devuelve el número correcto de caracteres._

2. **Caso de prueba 2:**  
   _Prueba con un archivo vacío_
    - Resultado esperado: _El contador debe devolver 0, ya que no hay caracteres en el archivo._
    - Resultado obtenido: _El contador devuelve correctamente 0._

3. **Caso de prueba 3:**  
   _Prueba con un archivo que no existe_
    - Resultado esperado: _El programa debe manejar la excepción correctamente y no bloquearse._
    - Resultado obtenido: _El programa lanza una excepción controlada y no se bloquea._

---

## 💡 Observaciones

- **Observaciones generales:**  
  Este ejercicio me permitió comprender mejor cómo manejar archivos de manera eficiente utilizando hilos en Java. Además, pude mejorar mi entendimiento sobre cómo manejar correctamente los recursos del sistema, como los flujos de entrada y salida, dentro de un entorno multihilo. La implementación de pruebas unitarias también demostró ser una herramienta útil para garantizar que el programa estuviera funcionando correctamente en diferentes condiciones de entrada.

---

> **Nota:**  
> Este documento fue creado para reflexionar sobre la calidad del trabajo realizado y fomentar la mejora continua. ✨
