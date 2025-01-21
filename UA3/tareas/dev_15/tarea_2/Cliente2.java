import java.io.*;
import java.net.*;

public class Cliente2 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 33;

        // Nos conectamos al servidor
        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Cliente 2 conectado al servidor.");

            // Leemos el mensaje que hemos puesto en el servidor cuando se conecta un cliente
            try (InputStream entrada = socket.getInputStream();
                 DataInputStream flujoEntrada = new DataInputStream(entrada)) {
                String mensajeServidor = flujoEntrada.readUTF(); // Leemos el mensaje del servidor
                System.out.println("Mensaje recibido del servidor: " + mensajeServidor);
            }

        } catch (IOException e) {
            System.out.println("Error en Cliente 2: " + e.getMessage());
        }
    }
}
