# Revisión de la Tarea 3
## Reflexión

- ¿Qué errores encontré en mi código?

    Aunque se captura la excepción IOException, el mensaje de error podría proporcionar más información útil, como sugerir si el archivo no existe o si hay un problema de permisos.
    Aunque Files.readString es eficiente y no necesita explícito cierre de recursos, el diseño podría considerar usar BufferedReader en caso de leer archivos más grandes para mejorar el rendimiento en sistemas con recursos limitados.
    El código no comprueba si el archivo existe antes de intentar leerlo, lo que podría provocar errores evitables.
    Al usar System.out.println desde múltiples hilos, la salida puede aparecer desordenada debido a la concurrencia.

- ¿Qué aprendí al corregirlo?

    La importancia de validar los archivos antes de intentar procesarlos.
    Cómo manejar correctamente errores y excepciones para proporcionar retroalimentación más útil.
    La utilidad de herramientas concurrentes en Java y cómo afectan la ejecución y salida.

- ¿Cómo me ayudaron los tests unitarios?

    Verifiqué que los resultados del conteo de caracteres fueran correctos para cada archivo.
    Probé cómo el programa responde cuando el archivo no existe o es inaccesible.
    Confirmé que la salida es correcta incluso con múltiples hilos procesando simultáneamente.

## Tests Implementados

- Caso de prueba 1: Descripción y resultado.

    Descripción: Verifica que el método cuenta correctamente el número de caracteres en un archivo dado.
    Resultado esperado: El número de caracteres reportado coincide con el tamaño real del contenido.

## Observaciones

- Observaciones generales sobre el ejercicio.

    Incluir validaciones antes de procesar un archivo mejora la robustez del programa.
    Usar una herramienta como java.util.concurrent.ExecutorService puede ayudar a gestionar la ejecución de los hilos y mantener orden en la salida.
    El código es eficiente para un pequeño número de archivos, pero podría mejorarse para manejar muchos archivos o tamaños de archivo grandes.