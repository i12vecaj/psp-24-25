import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) {
        // DATOS DE CONEXION
        final int PUERTO = 33;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO + "...");

            byte[] buffer = new byte[1024];

            while (true) {
                // Recibimos un paquete del cliente
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength()).trim();
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                // Si el mensaje es "*", finaliza la conexión con el cliente
                if (mensaje.equals("*")) {
                    System.out.println("Cliente " + direccionCliente + ":" + puertoCliente + " ha finalizado la conexión.");
                    continue;
                }

                // Convertimos el mensaje a mayúsculas
                String respuesta = mensaje.toUpperCase();
                byte[] datosRespuesta = respuesta.getBytes();

                // Enviamos la respuesta al cliente
                DatagramPacket paqueteRespuesta = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);

                System.out.println("Mensaje recibido: \"" + mensaje + "\" -> Respuesta enviada: \"" + respuesta + "\" a " + direccionCliente + ":" + puertoCliente);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}