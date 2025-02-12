import java.io.*;
import java.net.*;

public class Servidor {
  public static void main(String[] args) {
    final int puerto = 12345;

    try (ServerSocket serverSocket = new ServerSocket(puerto)) {
      System.out.println("Servidor esperando conexiones en el puerto " + puerto + "...");

      while (true) {
        try (Socket clientSocket = serverSocket.accept();
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

          System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

          Numeros num = (Numeros) in.readObject();
          System.out.println("Número recibido: " + num.getNumero());

          int n = num.getNumero();
          num.setCuadrado((long) n * n);
          num.setCubo((long) n * n * n);

          out.writeObject(num);
          System.out.println("Resultados enviados al cliente.");

        } catch (IOException | ClassNotFoundException e) {
          System.out.println("Error en la conexión con el cliente: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.out.println("Error en el servidor: " + e.getMessage());
    }
  }
}
