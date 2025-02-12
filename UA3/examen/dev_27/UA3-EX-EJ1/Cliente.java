import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 12345;

            System.out.println("Cliente UDP iniciado. Escribe un mensaje o 'SALIR' para salir.");

            while (true) {
                System.out.print("Tu: ");
                String mensaje = scanner.nextLine();

                if (mensaje.equals("SALIR")) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                byte[] bufferEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, serverAddress, serverPort);
                socket.send(paqueteEnvio);

                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socket.receive(paqueteRecepcion);

                String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("Servidor: " + respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}