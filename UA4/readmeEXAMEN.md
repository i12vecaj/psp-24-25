# Examen - API REST en Java 🚀

## Descripción 📜
En este ejercicio hemos creado una **API REST** en Java utilizando la librería **Spark**. La API permite gestionar personas mediante **identificadores enteros** en las URLs y proporciona las siguientes funcionalidades:

1. **Agregar personas** 👤
2. **Obtener personas por ID** 🔍
3. **Eliminar personas por ID** 🗑️

Todo esto se realiza de forma sencilla y rápida mediante **solicitudes HTTP**.

## Endpoints 🛠️

1. **Agregar persona**  
   - **Método**: `POST`  
   - **Ruta**: `/personas`  
   - **Parámetro**: `nombre`  
   - **Respuesta**: Persona agregada con un ID generado automáticamente.  
   - **Ejemplo**:  
     ```json
     { "nombre": "Juan" }
     ```

2. **Obtener persona por ID**  
   - **Método**: `GET`  
   - **Ruta**: `/personas/{id}`  
   - **Respuesta**: Datos de la persona correspondiente al `id`.  
   - **Ejemplo**:  
     - `GET /personas/1`

3. **Eliminar persona por ID**  
   - **Método**: `DELETE`  
   - **Ruta**: `/personas/{id}`  
   - **Respuesta**: Confirmación de eliminación o mensaje de error si no existe.  
   - **Ejemplo**:  
     - `DELETE /personas/1`

## Tecnologías 🛠️

- **Java 21** ☕
- **Spark Java** (para crear la API REST) ⚡
- **Maven** (para la gestión de dependencias) 📦

## Instrucciones de Ejecución ⚙️

1. Clona este repositorio.
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA o Eclipse).
3. Ejecuta el servidor con **Maven**:
   ```bash
   mvn clean install
   mvn exec:java
