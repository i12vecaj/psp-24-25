import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {
    private static int puerto = 12345;
    private static String ip_multicast = "230.0.0.0";

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress grupo = InetAddress.getByName(ip_multicast);
            socket.joinGroup(grupo);

            byte[] buffer = new byte[1024];
            
            System.out.println("-----------------------------------");
            System.out.println("Cliente escuchando en el puerto " + puerto);
            System.out.println("-----------------------------------");

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("-----------------------------------");
                System.out.println("Mensaje recibido: " + mensaje);
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
