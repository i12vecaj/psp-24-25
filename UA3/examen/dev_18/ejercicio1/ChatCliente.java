import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatCliente {
    private static final String Direccion_Server = "localhost";
    private static final int Puerto = 10100;

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(Direccion_Server);
        Scanner scanner = new Scanner(System.in);

        // Hilo para recibir mensajes
        new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String mensaje = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Mensaje recibido: " + mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Enviar mensajes y cerrar socket
        while (true) {
            System.out.print("Escribe tu mensaje (o pon 'SALIR' para terminar tu sesion): ");
            String mensaje = scanner.nextLine();
            if (mensaje.equalsIgnoreCase("SALIR")) {
                socket.close();
                break;
            }
            byte[] data = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, Puerto);
            socket.send(packet);
        }
    }
}