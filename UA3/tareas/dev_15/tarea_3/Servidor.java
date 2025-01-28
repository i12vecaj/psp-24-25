import java.net.*;

public class Servidor {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            // DATOS DE CONEXION
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("SERVIDOR ON ....");
            System.out.println("Esperando mensajes...");

            // Creamos un buffer para recibir los datos
            byte[] buffer = new byte[1024];

            while (true) {
                // Esto se activa cuando recibimos un paquete del cliente
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);

                // Extraemos el contenido del paquete del cliente
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Convertimos el mensaje a mayúsculas
                String mensajeEnMayusculas = mensaje.toUpperCase();

                // Preparamos la respuesta
                byte[] respuesta = mensajeEnMayusculas.getBytes();

                // Enviamos el paquete de vuelta al cliente
                DatagramPacket respuestaPacket = new DatagramPacket(
                        respuesta,
                        respuesta.length,
                        packet.getAddress(),
                        packet.getPort()
                );
                serverSocket.send(respuestaPacket);
                System.out.println("Respuesta enviada: " + mensajeEnMayusculas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}