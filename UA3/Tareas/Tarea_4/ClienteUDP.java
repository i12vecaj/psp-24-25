import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
  public static void main(String[] args) {
    String IP = "127.0.0.1"; // IP del servidor
    int puerto = 12345; // Puerto del servidor
    int TIMEOUT = 5000; // Tiempo de espera en milisegundos

    try (DatagramSocket clientSocket = new DatagramSocket();
         Scanner scanner = new Scanner(System.in)) {

      clientSocket.setSoTimeout(TIMEOUT); // Establece tiempo de espera para recibir respuesta

      InetAddress serverAddress = InetAddress.getByName(IP);
      System.out.println("Escribe un mensaje para enviar al servidor. Usa '*' para salir.");

      while (true) {
        System.out.print("Cliente: ");
        String message = scanner.nextLine();

        byte[] sendBuffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, puerto);
        clientSocket.send(sendPacket); // Enviar mensaje al servidor

        if (message.equals("*")) {
          System.out.println("Conexión cerrada.");
          break;
        }

        // Recibir respuesta del servidor
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        try {
          clientSocket.receive(receivePacket);
          String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
          System.out.println("Servidor: " + response);
        } catch (Exception e) {
          System.out.println("Error: No se recibió respuesta del servidor. Puede que el paquete se haya perdido.");
        }
      }
    } catch (Exception e) {
      System.out.println("Error en el cliente: " + e.getMessage());
    }
  }
}
