import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 5000;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PORT);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + received);

                if (received.equals("*")) {
                    System.out.println("Finalizando servidor...");
                    break;
                }

                String response = received.toUpperCase();
                byte[] responseData = response.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(
                        responseData, responseData.length,
                        packet.getAddress(), packet.getPort()
                );

                socket.send(responsePacket);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}