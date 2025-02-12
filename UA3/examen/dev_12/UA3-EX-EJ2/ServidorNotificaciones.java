package examenua3.parte11;

import java.net.*;
import java.util.*;

public class ServidorNotificaciones {
    private static final int PUERTO = 9000;
    private static final List<InetSocketAddress> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor activo en el puerto " + PUERTO);

            Thread recibirClientes = new Thread(() -> {
                try {
                    byte[] buffer = new byte[1024];

                    while (true) {
                        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paqueteRecibido);

                        InetSocketAddress cliente = new InetSocketAddress(
                                paqueteRecibido.getAddress(), paqueteRecibido.getPort());

                        if (!clientes.contains(cliente)) {
                            clientes.add(cliente);
                            System.out.println("Nuevo cliente registrado: " + cliente);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error recibiendo clientes: " + e.getMessage());
                }
            });

            recibirClientes.start();

            while (true) {
                System.out.print("Escribe una alerta para enviar a los clientes: ");
                String mensaje = scanner.nextLine();
                enviarNotificacion(mensaje, socket);
            }

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void enviarNotificacion(String mensaje, DatagramSocket socket) {
        byte[] buffer = mensaje.getBytes();

        for (InetSocketAddress cliente : clientes) {
            try {
                DatagramPacket paquete = new DatagramPacket(
                        buffer, buffer.length, cliente.getAddress(), cliente.getPort());
                socket.send(paquete);
                System.out.println("Notificación enviada a " + cliente);
            } catch (Exception e) {
                System.out.println("No se pudo enviar la notificación a " + cliente);
            }
        }
    }
}
