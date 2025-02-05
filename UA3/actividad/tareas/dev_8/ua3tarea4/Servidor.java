import java.net.*;
import java.io.IOException;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(9876);
            byte[] bufferRecepcionDatos = new byte[1024];
            String mensaje;

            do {
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcionDatos, bufferRecepcionDatos.length);
                socket.receive(paqueteRecepcion);

                mensaje = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                String respuesta = mensaje.toUpperCase();
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, paqueteRecepcion.getAddress(), paqueteRecepcion.getPort());
                socket.send(paqueteEnvio);
            } while (!mensaje.equals("*"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}