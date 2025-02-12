import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {

    public static void main(String[] args) {
        final int PUERTO = 6789;
        final int TAMANO_BUFFER = 1024;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PUERTO);

            while (true) {
                byte[] buffer = new byte[TAMANO_BUFFER];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String mensajeRecibido = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensajeRecibido);

                if (mensajeRecibido.equals("*")) {
                    System.out.println("Fin de la comunicación.");
                    break;
                }

                String mensajeEnMayusculas = mensajeRecibido.toUpperCase();
                byte[] bufferRespuesta = mensajeEnMayusculas.getBytes();

                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
