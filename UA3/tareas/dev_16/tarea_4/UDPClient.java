import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 5000;
        final int TIMEOUT = 5000;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un mensaje (* para salir): ");
                String message = scanner.nextLine();

                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                        InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);

                socket.send(packet);

                if (message.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                try {
                    byte[] responseBuffer = new byte[1024];
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                    socket.receive(responsePacket);

                    String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                    System.out.println("Respuesta del servidor: " + response);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El paquete podría haberse perdido.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}