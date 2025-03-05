package org.example;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Servidor en el puerto 8080

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/"); // Ruta base de la API
        server.setHandler(context);

        // 🔹 Registrar el servlet correctamente sin necesidad de instanciarlo manualmente
        context.addServlet((Class<? extends Servlet>) PersonServlet.class, "/people/*");

        server.start();
        System.out.println("🚀 Servidor Jetty corriendo en http://localhost:8080");
        server.join();
    }
}
