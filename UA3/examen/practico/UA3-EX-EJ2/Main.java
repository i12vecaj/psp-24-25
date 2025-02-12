import java.net.*;
import java.util.Scanner;

public class Main {
    private static final int puerto = 10005;
    private static final String conexionIp = "255.255.255.255";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Servidor de Notificaciones Iniciado...");

            while (true) {
                System.out.print("Ingrese notificación: ");
                String message = scanner.nextLine();
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(conexionIp), puerto);
                socket.send(packet);
                System.out.println("Notificación enviada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NotificationCliente {
    private static final int puerto = 10005;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1024];
            System.out.println("Cliente escuchando notificaciones...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Notificación recibida: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
