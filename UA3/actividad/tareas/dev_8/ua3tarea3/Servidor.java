import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            boolean encendido = true;

            do {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                String mensajeMayusculas = mensaje.toUpperCase();
                byte[] bufferRespuesta = mensajeMayusculas.getBytes();

                DatagramPacket packetRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, packet.getAddress(), packet.getPort());
                socket.send(packetRespuesta);
            } while (encendido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
