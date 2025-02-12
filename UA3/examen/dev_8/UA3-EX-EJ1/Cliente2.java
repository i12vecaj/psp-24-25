package UA3_EX_EJ1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Cliente2 {
    private MulticastSocket socket;
    private InetAddress group;
    private String clientId;

    public Cliente2() {
        setupMulticast();
        clientId = "Cliente2";
    }

    private void setupMulticast() {
        try {
            socket = new MulticastSocket();
            group = InetAddress.getByName("230.0.0.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            String fullMessage = clientId + ": " + message;
            byte[] buf = fullMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cliente2 iniciado. Escribe un mensaje (o SALIR para terminar):");
        while (true) {
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("SALIR")) {
                break;
            }
            sendMessage(message);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Cliente2 cliente = new Cliente2();
        cliente.start();
    }
}