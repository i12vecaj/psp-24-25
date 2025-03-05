package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // Crear un servidor HTTP en el puerto 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Registrar un manejador para la ruta "/api"
        server.createContext("/api", new MyHandler());

        // Iniciar el servidor
        server.start();
        System.out.println("Servidor iniciado en http://localhost:8080/api");
    }
}

class MyHandler implements HttpHandler {
    private Map<Integer, String> data = new HashMap<>(); // Almacenar los datos

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        switch (method) {
            case "GET":
                response = handleGetRequest(exchange);
                break;
            case "POST":
                response = handlePostRequest(exchange);
                break;
            case "PUT":
                response = handlePutRequest(exchange);
                break;
            case "DELETE":
                response = handleDeleteRequest(exchange);
                break;
            default:
                response = "Método no soportado";
                break;
        }

        // Enviar la respuesta
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private String handleGetRequest(HttpExchange exchange) {
        // Devolver todos los datos almacenados
        return "Datos: " + data.toString();
    }

    private String handlePostRequest(HttpExchange exchange) {
        // Crear un nuevo recurso
        int id = data.size() + 1;
        data.put(id, "Nuevo recurso " + id);
        return "Recurso creado con ID: " + id;
    }

    private String handlePutRequest(HttpExchange exchange) {
        // Actualizar un recurso existente
        int id = 1; // ID fijo para simplificar
        if (data.containsKey(id)) {
            data.put(id, "Recurso actualizado " + id);
            return "Recurso actualizado con ID: " + id;
        } else {
            return "Recurso no encontrado";
        }
    }

    private String handleDeleteRequest(HttpExchange exchange) {
        // Eliminar un recurso existente
        int id = 1; // ID fijo para simplificar
        if (data.containsKey(id)) {
            data.remove(id);
            return "Recurso eliminado con ID: " + id;
        } else {
            return "Recurso no encontrado";
        }
    }
}