 RESUMEN DEL PROCESO
 Creación del Proyecto en IntelliJ IDEA
Creamos un nuevo proyecto Maven en IntelliJ con el nombre examen2.
Configuramos pom.xml para asegurarnos de que el proyecto usa Java 17.
Recargamos Maven (Reload Project) para descargar y configurar las dependencias correctamente.
Implementación de la API REST en Java
Creamos el archivo SimpleApi.java dentro del paquete com.luismi.
Implementamos un servidor HTTP embebido en Java utilizando HttpServer que escucha en el puerto 8080.
Definimos tres endpoints:
GET /personas → Devuelve la lista de personas en formato JSON.
POST /agregar → Agrega una nueva persona con un ID automático.
DELETE /eliminar?id=1 → Elimina una persona según el ID pasado como parámetro.
 Ejecución de la API
Ejecutamos SimpleApi.java en IntelliJ (Run 'SimpleApi.main()').
Verificamos en la consola de IntelliJ que aparece el mensaje:


Servidor corriendo en http://localhost:8080/
Probamos en el navegador accediendo a:


http://localhost:8080/personas
Como aún no hemos agregado personas, devuelve {}.
Pruebas en Postman
Prueba 1: Agregar una persona (POST /agregar)
Abrimos Postman y creamos una nueva solicitud.
Configuramos:
Método: POST
URL: http://localhost:8080/agregar
Body: No es necesario incluir datos.
Hacemos clic en "Send" y obtenemos una respuesta:

Persona agregada con ID: 1

Si repetimos la solicitud, el ID aumenta:

Persona agregada con ID: 2

Prueba 2: Consultar la lista de personas (GET /personas)
En el navegador o en Postman, hacemos una solicitud GET a:



http://localhost:8080/personas
Ahora la respuesta no es {}, sino algo como:

{1=Persona1, 2=Persona2}

Prueba 3: Eliminar una persona (DELETE /eliminar?id=1)
En Postman, creamos una nueva solicitud:
Método: DELETE
URL: http://localhost:8080/eliminar?id=1
Hacemos clic en "Send" y recibimos:

Persona con ID 1 eliminada.

Si volvemos a hacer GET /personas, ya no aparecerá {1=Persona1}.
