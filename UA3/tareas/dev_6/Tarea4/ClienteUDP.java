import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost"; // Dirección del servidor
        final int PUERTO = 5000; // Puerto del servidor
        final int TIMEOUT = 5000; // Tiempo de espera en milisegundos (5 segundos)

        try {
            // Crear el socket del cliente
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT); // Configurar el tiempo de espera

            Scanner scanner = new Scanner(System.in);
            System.out.println("Cliente UDP iniciado. Escribe un mensaje para enviarlo al servidor.");
            System.out.println("Escribe '*' para finalizar.");

            while (true) {
                // Leer el mensaje desde la entrada estándar
                System.out.print("Tu mensaje: ");
                String mensaje = scanner.nextLine();

                // Enviar el mensaje al servidor
                byte[] buffer = mensaje.getBytes();
                InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO);
                socket.send(paquete);

                // Salir si el mensaje es un asterisco
                if (mensaje.equals("*")) {
                    System.out.println("Finalizando cliente...");
                    break;
                }

                try {
                    // Recibir la respuesta del servidor
                    byte[] bufferRespuesta = new byte[1024];
                    DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                    socket.receive(paqueteRespuesta);

                    // Mostrar la respuesta del servidor
                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (java.net.SocketTimeoutException e) {
                    // Mostrar un mensaje si no hay respuesta
                    System.out.println("No se recibió respuesta del servidor (tiempo agotado).");
                }
            }

            // Cerrar el socket
            socket.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
