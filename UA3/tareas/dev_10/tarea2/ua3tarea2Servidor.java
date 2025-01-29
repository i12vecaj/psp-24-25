package tarea2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ua3tarea2Servidor {
  public static void main(String[] args) {
    int puerto = 3307;
    int clientes = 2;

    try (ServerSocket serverSocket = new ServerSocket(puerto)) {
      System.out.println("Servidor iniciado en el puerto " + puerto);
      System.out.println("Esperando la conexión de " + clientes + " clientes...\n");


      for (int i = 1; i <= clientes; i++) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente " + i + " conectado.");
        System.out.println("  - Puerto local (ServerSocket): " + clientSocket.getLocalPort());
        System.out.println("  - Puerto remoto (Cliente): " + clientSocket.getPort());
        System.out.println("  - IP remota: " + clientSocket.getInetAddress());
        System.out.println("----------------------------------------------");
      }

      System.out.println("Se han conectado " + clientes + " clientes. Cerrando servidor...");


    } catch (IOException error) {// IOException captura errores de entrada/salida más generales.
      error.printStackTrace();
    }
  }
}
