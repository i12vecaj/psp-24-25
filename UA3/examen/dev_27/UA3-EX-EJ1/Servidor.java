import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            System.out.println("Servidor en ejecucion");

            while (true) {
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socket.receive(paqueteRecepcion);

                String mensaje = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());

                if (mensaje.equals("SALIR")) {
                    System.out.println("Cerrando servidor");
                    break;
                }

                String respuesta = "Mensaje recibido";
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,
                        paqueteRecepcion.getAddress(), paqueteRecepcion.getPort());
                socket.send(paqueteEnvio);
                System.out.println("IP de origen del paquete: "+paqueteRecepcion.getAddress());
                System.out.println("Puerto desde el que se envia: "+paqueteRecepcion.getPort());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}