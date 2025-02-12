import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NotificationServer {
    public static void main(String[] args) { // inicializacion del socket UDP del server. uso de DatagramPacket.
        try (DatagramSocket socket = new DatagramSocket()) {
            String mensaje = "WARNING: NEW NOTIFICATION FROM THE SYSTEM!";
            byte[] buffer = mensaje.getBytes();
            InetAddress direccion = InetAddress.getByName("255.255.255.255"); // DIFUSION
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 5000);

            socket.send(packet);
            System.out.println("Message: " + mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
