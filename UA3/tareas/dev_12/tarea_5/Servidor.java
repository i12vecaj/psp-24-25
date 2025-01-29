package tarea5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Servidor iniciado y esperando conexión...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado.");

                // Crear flujos de entrada y salida
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                // Leer objeto Numeros del cliente
                Numeros numeros = (Numeros) input.readObject();

                if (numeros.getNumeroEntero() >= 0) {
                    numeros.setCuadrado((long) Math.pow(numeros.getNumeroEntero(), 2));
                    numeros.setCubo((long) Math.pow(numeros.getNumeroEntero(), 3));
                } else {
                    System.out.println("Número inválido (menor que 0), no se calculan los valores.");
                }

                // Enviar el objeto de vuelta al cliente
                output.writeObject(numeros);
                output.flush();

                // Cerrar conexiones
                input.close();
                output.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
