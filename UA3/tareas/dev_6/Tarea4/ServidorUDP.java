import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) {
        final int PUERTO = 5000; // Puerto del servidor

        try {
            // Crear el socket para escuchar en el puerto
            DatagramSocket socket = new DatagramSocket(PUERTO);
            System.out.println("Servidor UDP iniciado en el puerto " + PUERTO);

            while (true) {
                // Preparar el buffer para recibir datos
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

                // Recibir el paquete del cliente
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Verificar si el mensaje es un asterisco para salir
                if (mensaje.equals("*")) {
                    System.out.println("Asterisco recibido. Finalizando servidor...");
                    break;
                }

                // Convertir el mensaje a mayúsculas
                String respuesta = mensaje.toUpperCase();

                // Enviar la respuesta al cliente
                byte[] datosRespuesta = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        datosRespuesta,
                        datosRespuesta.length,
                        paqueteRecibido.getAddress(),
                        paqueteRecibido.getPort()
                );
                socket.send(paqueteRespuesta);
                System.out.println("Respuesta enviada: " + respuesta);
            }

            // Cerrar el socket
            socket.close();
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
