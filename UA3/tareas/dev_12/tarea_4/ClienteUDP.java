package tarea4;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        String servidorIP = "localhost"; // IP del servidor
        int puerto = 5000; // Puerto del servidor
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000); // Tiempo de espera de 5 segundos

            System.out.println("Cliente UDP listo. Escribe un mensaje:");
            
            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();

                byte[] bufferMensaje = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(bufferMensaje, bufferMensaje.length, InetAddress.getByName(servidorIP), puerto);
                socket.send(paquete);

                if (mensaje.equals("*")) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

                try {
                    socket.receive(paqueteRespuesta);
                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Servidor responde: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("⚠️ No se recibió respuesta del servidor. Puede que el paquete se haya perdido.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }

        scanner.close();
    }
}
