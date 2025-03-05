package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Crear una instancia de HttpServer en el puerto 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Registrar el servlet PersonServlet en el contexto "/people/*"
        server.createContext("/people", new PersonHandler());

        // Iniciar el servidor
        server.start();
        System.out.println("Server started on port 8080");
    }
}