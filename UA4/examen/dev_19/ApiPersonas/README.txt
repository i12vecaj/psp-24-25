IMPORTANTE
Configurar los paquetes de los archivos para que funcione bien
EXPLICACION
Clase Person
Define un objeto Persona con propiedades como id, name, about, y birthYear. Tiene un constructor y métodos getters y setters para acceder y modificar los valores.

Clase PersonServlet
Es el servlet que maneja las solicitudes HTTP. Está mapeado a la URL /api/persons y tiene tres métodos principales para manejar las solicitudes:

doGet:
Si no se pasa un ID, devuelve todas las personas.
Si se pasa un ID, devuelve solo esa persona.
Si la persona no se encuentra, devuelve un error 404.

doPost:
Crea una nueva persona a partir del JSON recibido en la solicitud.
Asigna un ID único a la persona.
Devuelve la persona creada.

doDelete:
Elimina una persona por ID.
Si la persona no existe, devuelve un error 404.

Datos en Memoria
personDatabase: Un Map que simula una base de datos, donde las personas se almacenan por su ID.
currentId: Se utiliza para generar un ID único para cada nueva persona.

TESTEO API
Abre la aplicación Postman y crea o inicia sesión en tu cuenta.

Configura las solicitudes:
GET /api/persons: 
Solicitud GET a http://localhost:8080/api/persons para obtener todas las personas.
GET /api/persons/{id}: 
Solicitud GET a http://localhost:8080/api/persons/{id} para obtener una persona por ID.
POST /api/persons: 
Solicitud POST a http://localhost:8080/api/persons, con el cuerpo en formato JSON (selecciona raw y JSON).
DELETE /api/persons/{id}: 
Solicitud DELETE a http://localhost:8080/api/persons/{id} para eliminar una persona por ID.

Haz clic en "Send" para enviar la solicitud y ver las respuestas en formato JSON.

Con esto la API ya se ha probado

USO DE IA
He usado la IA (ChatGPT) para hacer ingenieria inversa a partir del enunciado y el Json que aparecia en la página.
Luego he creado la estructura del proyecto manualmente y he seguido las indicaciones de la IA.
Por último he descargado Postman para poder probar la API en modo local y comprobar su funcionamiento
