import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        // DATOS DE CONEXION
        final String SERVIDOR = "127.0.0.1";
        final int PUERTO = 33;
        final int TIMEOUT = 3000;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escribe un mensaje para enviar al servidor, (si quieres salir usa '*' para salir del programa).");

            while (true) {
                System.out.print("Mensaje: ");
                String mensaje = scanner.nextLine();

                // Enviamos mensaje al servidor
                byte[] datosEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(datosEnvio, datosEnvio.length, direccionServidor, PUERTO);
                socket.send(paqueteEnvio);

                // Si el usuario envía "*", termina el programa
                if (mensaje.equals("*")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                // Recibimos respuesta del servidor
                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

                try {
                    socket.receive(paqueteRespuesta);
                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (Exception e) {
                    System.out.println("Tiempo de espera agotado. Parece que el servidor no responde.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}