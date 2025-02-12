import java.net.*;

public class ServidorNotificaciones {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress direccionGrupo = InetAddress.getByName("225.0.0.1");
        int puerto = 12345;

        String mensaje = "Atencion esto es un mensaje urgente: Este fin de semana trabaja toda la plantilla !";

        byte[] buffer = mensaje.getBytes();

        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionGrupo, puerto);
        socket.send(paquete);

        System.out.println("Notificación enviada a todos los clientes.");
        socket.close();
    }
}
