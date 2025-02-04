import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Cliente conectado.");

                    // Leer objeto del cliente
                    Numeros numeros = (Numeros) in.readObject();

                    // Calcular cuadrado y cubo
                    int numero = numeros.getNumero();
                    numeros.setCuadrado((long) numero * numero);
                    numeros.setCubo((long) numero * numero * numero);

                    // Enviar objeto modificado de vuelta al cliente
                    out.writeObject(numeros);

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error al procesar la solicitud del cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
