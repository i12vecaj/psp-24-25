import java.net.*;

public class ServidorToken {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = new byte[1];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 10000);
            socket.send(paquete);
            System.out.println("Servidor ha enviado el primer token al puerto 10000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}