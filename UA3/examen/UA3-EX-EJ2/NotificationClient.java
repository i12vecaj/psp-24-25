import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NotificationClient {
    public static void main(String[] args) {

        //inicializacion de socket UDP en el cliente. se hace uso de DatagramSocket y de un Array byte[].

        try (DatagramSocket socket = new DatagramSocket(5000)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Client waiting for notification...");
            socket.receive(packet);

            String mensaje = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Notification received: " + mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
