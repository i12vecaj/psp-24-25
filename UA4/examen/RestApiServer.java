package com.example.restapi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;



public class RestApiServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Puerto 8080
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        // Registrar el servlet de la API
        context.addServlet(new ServletHolder(new MyApiServlet()), "/api/*");

        server.setHandler(context);
        server.start();
        server.join();
    }
}
