# 📝 Revisión de la Tarea X

## 📌 Reflexión
- *¿Qué errores encontré en mi código?*  
  Se me presentaros errores como condiciones de carrera al acceder al contador de caracteres. Además, al trabajar con archivos, me di cuenta de que no estaba manejando adecuadamente la excepción `FileNotFoundException`.

- *¿Qué aprendí al corregirlo?*  
  Es importante asegurarse de que los recursos compartidos, como el contador, estén protegidos correctamente (por ejemplo, utilizando sincronización).

- *¿Cómo me ayudaron los tests unitarios?*  
  Los tests me ayudaron a verificar que los hilos no interfieran entre sí, que los caracteres se cuenten correctamente y que el código maneje bien las excepciones (como la ausencia de archivos). Además, pude confirmar que el código funcionaba en escenarios más reales, como la lectura concurrente de varios archivos.

---

## ✅ Tests Implementados
1. *Caso de prueba 1:*  
   Verifica el conteo de caracteres en un archivo.
    - Resultado esperado: 35 (el número de caracteres en "Hola, este es un archivo de prueba.")
    - Resultado obtenido: 35

2. *Caso de prueba 2:*  
   Verifica que el conteo de caracteres funcione correctamente con múltiples hilos.
    - Resultado esperado: 35 para cada hilo (cada hilo cuenta los caracteres correctamente).
    - Resultado obtenido: 35 para cada hilo

3. *Caso de prueba 3:*  
   Verifica que no se cuenten caracteres cuando el archivo no existe.
    - Resultado esperado: 0 (el contador no debe incrementarse si el archivo no se encuentra).
    - Resultado obtenido: 0

---

## 💡 Observaciones
- *Observaciones generales:*  
  Aunque los hilos permiten realizar el trabajo concurrentemente, debo tener cuidado con la sincronización de los recursos compartidos, como el contador de caracteres.

---

> *Nota:*  
> En el apartado de que aprendí no se me ocurria otra cosa, por eso en todos pongo casi todos lo mismo al igual que con los test
