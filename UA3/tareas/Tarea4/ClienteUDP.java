import java.io.*;
import java.net.*;

public class ClienteUDP {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 10045;
    private static final int TIMEOUT = 5000; 

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Escribe texto (o * para salir): ");
                String input = reader.readLine().trim();
                if (input.isEmpty()) continue;
                
                byte[] sendBuffer = input.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, sendBuffer.length, serverAddress, SERVER_PORT
                );
                socket.send(sendPacket);
                if (input.equals("*")) break;
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                try {
                    socket.receive(receivePacket);
                    String respuesta = new String(
                        receivePacket.getData(), 0, receivePacket.getLength()
                    );
                    System.out.println("Respuesta: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("¡Tiempo agotado! Paquete perdido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}