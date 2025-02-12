package UA3_EX_EJ2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ServerUDP {
    private MulticastSocket socket;
    private InetAddress group;
    private int port;

    public ServerUDP(String multicastAddress, int port) {
        try {
            this.port = port;
            socket = new MulticastSocket();
            group = InetAddress.getByName(multicastAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void envioNotificaciones(String message) {
        try {
            byte[] buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
            socket.send(packet);
            System.out.println("Alerta enviada: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerUDP server = new ServerUDP("230.0.0.0", 4445);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce las notificaciones (escribe 'salir' para terminar)");
        while (true) {
            String alerta = scanner.nextLine();

            server.envioNotificaciones(alerta);
            if (alerta.equalsIgnoreCase("salir")) {
                break;
            }
        }
        scanner.close();
    }
}