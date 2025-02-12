import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        // Creamos un socket para escuchar en el puerto 12345
        DatagramSocket socket = new DatagramSocket(12345);

        // Creamos un búfer para recibir mensajes
        byte[] buffer = new byte[1024];

        while (true) {
            // Recibimos un mensaje del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Mostramos el mensaje en la consola
            System.out.println("Mensaje de Yisus dice : " + new String(packet.getData(), 0, packet.getLength()));

            // Enviamos la respuesta al cliente
            String respuesta = "Respuesta del servidor: " + new String(packet.getData(), 0, packet.getLength());
            byte[] respuestaBytes = respuesta.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(respuestaBytes, respuestaBytes.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);

        
        }
    }
}

