import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(6000)) {
            System.out.println("Servidor esperando conexiones...");
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength()).trim();
                System.out.println("Recibido: " + mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Asterisco recibido. Cerrando servidor...");
                    break;
                }

                String respuesta = mensaje.toUpperCase();
                byte[] enviar = respuesta.getBytes();
                InetAddress clienteIP = paquete.getAddress();
                int puertoCliente = paquete.getPort();
                DatagramPacket paqueteRespuesta = new DatagramPacket(enviar, enviar.length, clienteIP, puertoCliente);
                serverSocket.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
