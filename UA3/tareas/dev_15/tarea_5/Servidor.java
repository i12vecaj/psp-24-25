import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {

        //DATOS DEL SERVIDOR
        int PUERTO = 33;

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor en espera de conexiones en el puerto " + PUERTO + "...");

            while (true) {
                try (Socket socketCliente = servidor.accept();
                     ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream());
                     ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream())) {

                    System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());

                    // Recibimos el objeto del cliente
                    Numeros numeros = (Numeros) entrada.readObject();

                    // Calculamos el cuadrado y el cubo
                    numeros.setCuadrado((long) numeros.getNumero() * numeros.getNumero());
                    numeros.setCubo((long) numeros.getNumero() * numeros.getNumero() * numeros.getNumero());

                    // Enviamos el objeto calculado al cliente
                    salida.writeObject(numeros);
                    System.out.println("Enviado: " + numeros);

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
