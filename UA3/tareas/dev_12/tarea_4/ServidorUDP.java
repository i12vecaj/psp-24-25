package tarea4;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 5000; // Puerto donde escucha el servidor
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP esperando mensajes en el puerto " + puerto);

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                System.out.println("Cliente dice: " + mensaje);

                if (mensaje.equals("*")) {
                    System.out.println("Se recibió '*', cerrando servidor.");
                    break;
                }

                String respuesta = mensaje.toUpperCase();
                byte[] bufferRespuesta = respuesta.getBytes();

                DatagramPacket paqueteRespuesta = new DatagramPacket(
                    bufferRespuesta, bufferRespuesta.length,
                    paqueteRecibido.getAddress(), paqueteRecibido.getPort()
                );
                socket.send(paqueteRespuesta);
            }

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

