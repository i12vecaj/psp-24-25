import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class clienteNoti {
    private static final String entregaPuertoCliente = "224.0.0.1"; //Ip del cliente
    private static final int puerto = 10001; //Puerto del cliente por el que le llegara los mensajes

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress grupo = InetAddress.getByName(entregaPuertoCliente);
            socket.joinGroup(grupo);

            System.out.println("👂 Cliente escuchando notificaciones...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("📖 Notificación recibida: " + mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
