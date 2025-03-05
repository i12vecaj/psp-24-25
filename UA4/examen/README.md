# API REST de Gestión de Personas

Este proyecto es una API REST desarrollada en Java utilizando el framework **SparkJava**. La API permite gestionar personas (crear, leer, actualizar y eliminar) y está diseñada para ser consumida desde una interfaz web simple.

## 🚀 Características

- **Crear personas**: Añade nuevas personas con un nombre.
- **Obtener personas**: Lista todas las personas o una persona específica por su ID.
- **Actualizar personas**: Modifica el nombre de una persona existente.
- **Eeliminar personas**: Elimina una persona por su ID.
- **Interfaz web**: Una página HTML que consume la API y muestra la lista de personas.

## 🛠️ Tecnologías utilizadas

- **Java**: Lenguaje de programación principal.
- **SparkJava**: Micro-framework para crear APIs REST.
- **Gson**: Biblioteca para convertir objetos Java a JSON y viceversa.
- **HTML y JavaScript**: Para la interfaz web que consume la API.
- **Logback**: Para el registro de logs en la consola.

## 📂 Estructura del proyecto
ApiRestEXAMEN/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── org/
│ │ │ └── example/
│ │ │ ├── api/
│ │ │ │ ├── Person.java
│ │ │ │ ├── PersonRepository.java
│ │ │ │ └── RestAPI.java
│ │ └── resources/
│ │ └── logback.xml
│ └── test/
├── target/
└── index.html

## 🧩 Componentes principales

### 1. **Clase `Person`**
Representa a una persona con dos atributos:
- `id`: Identificador único.
- `name`: Nombre de la persona.

### 2. **Clase `PersonRepository`**
Actúa como una base de datos en memoria para almacenar personas. Proporciona métodos para:
- Añadir personas.
- Obtener personas por ID o todas las personas.
- Actualizar personas.
- Eliminar personas.

### 3. **Clase `RestAPI`**
Contiene los endpoints de la API:
- **POST `/people`**: Añade una nueva persona.
- **GET `/people`**: Obtiene todas las personas.
- **GET `/people/:id`**: Obtiene una persona por su ID.
- **PUT `/people/:id`**: Actualiza una persona por su ID.
- **DELETE `/people/:id`**: Elimina una persona por su ID.

Además, se ha habilitado **CORS** para permitir solicitudes desde cualquier origen.

### 4. **Interfaz web (`index.html`)**
Una página HTML simple que consume la API y muestra la lista de personas. Utiliza `fetch` para realizar solicitudes HTTP y actualizar el DOM dinámicamente.

### 5. **Configuración de logs (`logback.xml`)**
Configura el registro de logs en la consola con un formato específico.

## 🚀 Cómo ejecutar el proyecto

### Requisitos
- **Java JDK 17 o superior**.
- **Maven** para gestionar dependencias.

### Pasos

Compila y ejecuta la API:

bash
Copy
mvn clean package
java -cp target/classes org.example.api.RestAPI
Abre la interfaz web:

Sirve el archivo index.html usando un servidor local (por ejemplo, con Live Server o Python HTTP Server).

Abre tu navegador y visita http://localhost:4567/people o iniciando el index.html

Prueba la API:

Usa Postman o cURL para probar los endpoints:

# Añadir una persona
curl -X POST -d "name=Juan" http://localhost:4567/people

# Obtener todas las personas
curl http://localhost:4567/people

# Obtener una persona por ID
curl http://localhost:4567/people/1

# Actualizar una persona
curl -X PUT -d "name=Carlos" http://localhost:4567/people/1

# Eliminar una persona
curl -X DELETE http://localhost:4567/people/1

🌐 Endpoints de la API
Método	Endpoint	Descripción

POST	/people	Añade una nueva persona.
GET	/people	Obtiene todas las personas.
GET	/people/:id	Obtiene una persona por su ID.
PUT	/people/:id	Actualiza una persona por su ID.
DELETE	/people/:id	Elimina una persona por su ID.

🖼️ Capturas de pantalla
Interfaz web
![image](https://github.com/user-attachments/assets/86779b0f-fe62-49ad-90b1-933dcb1fea38)


Respuesta de la API
![image](https://github.com/user-attachments/assets/5ff4d2c3-5296-4abd-a29e-ae02a44d4e1e)
