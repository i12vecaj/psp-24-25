# Revisión de la Tarea 3 Tema 2
## Reflexión
- ¿Qué errores encontré en mi código?
    El archivo especificado en FileReader usa una ruta fija con "src/", lo que puede causar problemas si el archivo no está en esa ubicación. Esto no se valida antes de intentar leer.

    Si el archivo no existe o hay un error de permisos, no se maneja de manera específica aparte de mostrar un mensaje de error.

- ¿Qué aprendí al corregirlo?
    La importancia de validar rutas de archivos antes de procesarlos.

    La necesidad de manejar excepciones para diferentes casos de error, como archivos inexistentes o inaccesibles.

- ¿Cómo me ayudaron los tests unitarios?
    Al implementar pruebas con archivos de prueba, se pudo identificar que el manejo de rutas no era dinámico.

    Las pruebas también evidenciaron la necesidad de verificar casos de archivos vacíos o con caracteres especiales.

## Tests Implementados
- Caso de prueba 1: Descripción y resultado.
    Descripción: Crear un archivo de texto válido con contenido, y asegurarse de que el número de caracteres sea contado correctamente.

    Resultado esperado: Se muestra el número correcto de caracteres en el archivo.

- Caso de prueba 2: Archivo vacío.
    Descripción: Probar con un archivo vacío para verificar que el programa no genere errores y cuente 0 caracteres.

    Resultado esperado: Se muestra que el archivo tiene 0 caracteres.

- Caso de prueba 3: Archivo inexistente.
    Descripción: Intentar procesar un archivo que no existe en la ruta especificada.

    Resultado esperado: Se imprime un mensaje de error indicando que el archivo no pudo ser encontrado.

-Caso de prueba 4: Múltiples hilos.
    Descripción: Probar con varios archivos en paralelo para asegurar que los hilos se ejecuten y finalicen correctamente.

    Resultado esperado: Todos los archivos son procesados y los resultados son mostrados sin bloqueos o errores.

## Observaciones
- Observaciones generales sobre el ejercicio.

    Es recomendable hacer que la ruta de los archivos sea dinámica o permitir al usuario especificarla.

    Los hilos funcionan correctamente y se gestionan bien al usar join(), lo que garantiza que el proceso principal espere su finalización.

    Es útil agregar más detalles al mensaje de error para ayudar al usuario a solucionar el problema, como "Asegúrate de que el archivo exista en la ruta esperada."
