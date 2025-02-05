import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class servidor {
    public static void main(String[] args) {
        final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PUERTO);

            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socket.receive(peticion);

                String mensaje = new String(peticion.getData(), 0, peticion.getLength());

                if (mensaje.trim().equals("*")) {
                    System.out.println("Servidor detenido.");
                    break;
                }

                String respuesta = mensaje.toUpperCase();
                byte[] datosRespuesta = respuesta.getBytes();

                DatagramPacket paqueteRespuesta = new DatagramPacket(datosRespuesta, datosRespuesta.length, peticion.getAddress(), peticion.getPort());

                socket.send(paqueteRespuesta);
                System.out.println("Mensaje recibido: " + mensaje + " | Respuesta enviada: " + respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
