# Examen - API REST en Java

## Descripción
En este ejercicio se ha desarrollado una *API REST* utilizando *Java* y la librería *Spark*. La API permite realizar las siguientes operaciones sobre personas:

1. *Agregar personas*
2. *Consultar personas por su ID*
3. *Eliminar personas por su ID*

Las operaciones se realizan mediante peticiones HTTP con rutas específicas.

## Endpoints

1. *Agregar una persona*  
   - *Método*: POST  
   - *Ruta*: /personas  
   - *Parámetro*: nombre  
   - *Respuesta*: Se devuelve la persona agregada con un ID único.  
   - *Ejemplo de solicitud*:  
     json
     { "nombre": "Juan" }
     

2. *Obtener una persona por ID*  
   - *Método*: GET  
   - *Ruta*: /personas/{id}  
   - *Respuesta*: Se devuelve la persona correspondiente al id.  
   - *Ejemplo de solicitud*:  
     - GET /personas/1

3. *Eliminar una persona por ID*  
   - *Método*: DELETE  
   - *Ruta*: /personas/{id}  
   - *Respuesta*: Confirmación de la eliminación o mensaje de error si la persona no existe.  
   - *Ejemplo de solicitud*:  
     - DELETE /personas/1

## Tecnologías

- *Java 21*
- *Spark Java* (para la implementación de la API REST)
- *Maven* (para la gestión de dependencias)

## Instrucciones para ejecutar el proyecto

1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA o Eclipse).
3. Para ejecutar el servidor, utiliza los siguientes comandos en la terminal:
   ```bash
   mvn clean install
   mvn exec:java
