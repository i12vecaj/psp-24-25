import java.net.*;

public class ClienteNotificaciones {
    public static void main(String[] args) throws Exception {
        MulticastSocket socket = new MulticastSocket(12345);
        InetAddress direccionGrupo = InetAddress.getByName("225.0.0.1");
        socket.joinGroup(direccionGrupo);

        byte[] buffer = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        System.out.println("Esperando notificaciones...");
        socket.receive(paquete);

        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
        System.out.println("Notificación recibida: " + mensaje);

        socket.leaveGroup(direccionGrupo);
        socket.close();
    }
}

