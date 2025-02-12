import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
  public static void main(String[] args) {
    int puerto = 12345; // Puerto de escucha del servidor

    try (DatagramSocket serverSocket = new DatagramSocket(puerto)) {
      System.out.println("Servidor UDP escuchando en el puerto " + puerto);

      byte[] receiveBuffer = new byte[1024];

      while (true) {
        // Recibir datos del cliente
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        serverSocket.receive(receivePacket);

        // Convertir datos a String
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        System.out.println("Mensaje recibido de " + clientAddress + ":" + clientPort + " -> " + receivedMessage);

        if (receivedMessage.equals("*")) {
          System.out.println("Cliente finalizó la conexión.");
          break;
        }

        // Convertir el mensaje a mayúsculas
        String responseMessage = receivedMessage.toUpperCase();
        byte[] sendBuffer = responseMessage.getBytes();

        // Enviar respuesta al cliente
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);
      }

      System.out.println("Servidor apagado.");
    } catch (Exception e) {
      System.out.println("Error en el servidor: " + e.getMessage());
    }
  }
}