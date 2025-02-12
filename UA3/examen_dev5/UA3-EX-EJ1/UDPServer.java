

/* Debes justificar tu elección entre TCP o UDP en cada caso.
* he usado el udp porque me parece bastante bueno a la hora de enviar los datos, aunque sea una aplicación sencilla tiene que tener también cierta seguridad */

/*Pregunta para el estudiante:
¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.
*  Ami parecer como estudiante en cada caso yo he probado los dos, pero me resulta mas interesante en este caso usar udp por el sencillo motivo de que me parece sencillo de aplicar el ejercicio de esta manera por como he planteado la actividad.
* */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPServer {
  public static void main(String[] args) {
    final int PORT = 8000;
    byte[] buffer = new byte[1024];

    try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
      System.out.println("Servidor UDP escuchando en el puerto " + PORT);

      while (true) {
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(receivePacket);

        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Mensaje recibido de " + receivePacket.getAddress() + ": " + message);

        if (message.equals("*")) {
          System.out.println("Se recibió la orden 66 , cerrando servidor...");
          break;
        }
        if(message.equals( "salir")){
          System.out.println("Se recibió la orden 67, cerrando servidor...");
          break;
        }

        String response = message.toLowerCase();
        byte[] responseData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacket);
      }
    } catch (IOException e) {
      System.out.println("Error en el servidor: " + e.getMessage());
    }
  }
}

class UDPClient {
  public static void main(String[] args) {
    final String SERVER_ADDRESS = "127.0.0.1";
    final int SERVER_PORT = 8000;
    byte[] buffer = new byte[1024];
    Scanner scanner = new Scanner(System.in);

    try (DatagramSocket clientSocket = new DatagramSocket()) {
      clientSocket.setSoTimeout(5000);
      System.out.println("Cliente listo. Escriba mensajes para enviar (use '*' para salir o ponga salir):");

      while (true) {
        System.out.print("Mensaje: ");
        String message = scanner.nextLine();

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        clientSocket.send(sendPacket);

        if (message.equals("*")) {
          System.out.println("Se recibió  la orden 66, cerrando cliente...");
          break;
        }
        if (message.equalsIgnoreCase("salir")) {
          System.out.println("Se recibió  la orden 67, cerrando cliente...");
          break;
        }

        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        try {
          clientSocket.receive(receivePacket);
          String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
          System.out.println("Respuesta del servidor: " + receivedMessage);
        } catch (SocketTimeoutException e) {
          System.out.println("Tiempo de espera agotado. El servidor no respondió.");
        }
      }
    } catch (IOException e) {
      System.out.println("Error en el cliente: " + e.getMessage());
    }
  }
}
