import java.io.*;
import java.net.*;

public class ServidorUDP {
    private static final int PORT = 10045;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println(" Servidor activo en puerto " + PORT);
            byte[] buffer = new byte[1024];
            
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String mensaje = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println(" Recibido de " + packet.getAddress() + ": " + mensaje);
                
                if (mensaje.equals("*")) {
                    System.out.println("🔌Cerrando servidor...");
                    break;
                }
                
                String respuesta = mensaje.toUpperCase();
                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket respuestaPacket = new DatagramPacket(
                    respuestaBytes, respuestaBytes.length,
                    packet.getAddress(), packet.getPort()
                );
                socket.send(respuestaPacket);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}