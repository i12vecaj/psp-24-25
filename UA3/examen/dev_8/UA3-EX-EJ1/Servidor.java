
package UA3_EX_EJ1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Servidor {
    private MulticastSocket socket;
    private InetAddress group;
    private boolean running;

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.startServer();
    }

    private void startServer() {
        try {
            socket = new MulticastSocket(4446);
            group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);
            running = true;

            new Thread(() -> {
                System.out.println("Server Activo");
                while (running) {

                    try {
                        byte[] buf = new byte[256];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);
                        String received = new String(packet.getData(), 0, packet.getLength());
                        String clientAddress = packet.getAddress().toString();
                        addMessage(clientAddress + ": " + received);
                        if (received.equalsIgnoreCase("salir")) {
                            running = false;
                            socket.leaveGroup(group);
                            socket.close();
                            System.out.println("Server parado");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Server no activo");
        }
    }

    private void addMessage(String message) {
        System.out.println(message);
    }
}