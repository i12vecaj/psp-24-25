import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatServer1 {
    public static void main(String[] args) throws IOException {
        // Creamos un socket para escuchar en el puerto 12345
        DatagramSocket socketUDP = new DatagramSocket(12345);
        byte[] buffer = new byte[1024];

        while (true) {
            // Recibimos un mensaje del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(packet);

            // Mostramos el mensaje en la consola
            System.out.println("Mensaje de Jezus dice : " + new String(packet.getData(), 0, packet.getLength()));

            // Enviamos la respuesta al cliente
            String respuesta = "Respuesta del servidor: " + new String(packet.getData(), 0, packet.getLength());
            byte[] respuestaBytes = respuesta.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(respuestaBytes, respuestaBytes.length, packet.getAddress(), packet.getPort());
            socketUDP.send(responsePacket);
        
        }
    }
}


