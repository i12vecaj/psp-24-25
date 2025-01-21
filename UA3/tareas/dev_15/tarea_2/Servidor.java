import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        // Este es el puerto donde el servidor esperará a que se conecten los clientes
        int puerto = 33;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("SERVIDOR ON....");
            System.out.println("Esperando clientes ...");
            System.out.println("_______________________________________________________________________________");

            // Permitimos dos conexiones al servidor
            for (int i = 1; i <= 2; i++) {
                // El servidor espera hasta que se conecte un cliente
                Socket cliente = serverSocket.accept();

                // Mostramos información del cliente
                System.out.println("Cliente " + i + " conectado:");
                System.out.println("Puerto local: " + cliente.getLocalPort());
                System.out.println("Puerto remoto: " + cliente.getPort());
                System.out.println("Dirección remota: " + cliente.getInetAddress());

                // Enviamos un mensaje al cliente
                try (OutputStream salida = cliente.getOutputStream();
                     DataOutputStream flujoSalida = new DataOutputStream(salida)) {
                    flujoSalida.writeUTF("Hola Cliente " + i + ", estas dentro del servidor...");
                } catch (IOException e) {
                    //Mostramos un error si no podemos enviar el mensaje al cliente
                    System.out.println("Error al enviar mensaje al cliente " + i + ": " + e.getMessage());
                }
                // Cerramos la conexión del cliente
                cliente.close();
            }

            System.out.println("Todos los clientes han sido atendidos. Cerrando el servidor...");

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}