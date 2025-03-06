package org.example;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;

public class JSSEExample {
    public static void main(String[] args) {
        try {
            // Establecer las propiedades del keystore
            System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "password");

            // Especificar el protocolo SSL/TLS que se desea usar
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new SecureRandom());

            // Crear el socket del servidor con el contexto SSL personalizado
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(8443);
            System.out.println("Servidor SSL iniciado, esperando conexiones...");

            // Aceptar la conexión
            SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Enviar mensaje al cliente
            writer.println("Conexión SSL establecida");

            // Leer mensaje del cliente
            String clientMessage = reader.readLine();
            System.out.println("Mensaje del cliente: " + clientMessage);

            // Cerrar conexiones
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
