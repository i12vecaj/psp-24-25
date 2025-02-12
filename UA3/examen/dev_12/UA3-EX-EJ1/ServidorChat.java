package examenua3.parte1.parte2;

import java.net.*;
import java.util.*;

public class ServidorChat {
    private static final int PUERTO = 9700;
    private static final List<InetSocketAddress> clientes = new ArrayList<>();

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor activo en el puerto " + PUERTO);

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                InetSocketAddress cliente = new InetSocketAddress(paqueteRecibido.getAddress(), paqueteRecibido.getPort());

                if (!clientes.contains(cliente)) {
                    clientes.add(cliente);
                }

                System.out.println("Mensaje Entrante: " + cliente + " dice: " + mensaje);

                //Si algun cliente sale del chat, lo indicamos a los demas
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Cliente " + cliente + " se ha desconectado.");
                    clientes.remove(cliente);
                    continue;
                }

                //Enviamos el mensaje a todos los clientes
                for (InetSocketAddress c : clientes) {
                    byte[] bufferRespuesta = mensaje.getBytes();
                    DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, c.getAddress(), c.getPort());
                    socket.send(paqueteRespuesta);
                }
            }

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

