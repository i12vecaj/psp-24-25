import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            System.out.println("Servidor UDP en ejecucion...");

            while (true) {
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socket.receive(paqueteRecepcion);

                String mensaje = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());

                if (mensaje.equals("*")) {
                    System.out.println("Se recibio '*', cerrando servidor...");
                    break;
                }

                String respuesta = mensaje.toUpperCase();
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,
                        paqueteRecepcion.getAddress(), paqueteRecepcion.getPort());
                socket.send(paqueteEnvio);

                System.out.println("Mensaje procesado: " + respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
