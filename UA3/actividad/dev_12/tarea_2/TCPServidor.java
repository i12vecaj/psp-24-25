package TCPEjemplo;

import java.io.*;
import java.net.*;

public class TCPServidor {
  public static void main(String[] arg) throws IOException {
    int numeroPuerto = 6000;
    ServerSocket servidor = new ServerSocket(numeroPuerto);
    System.out.println("Esperando al cliente.....");

    for (int i = 1; i <= 2; i++) {
      Socket clienteConectado = servidor.accept();
      System.out.println("Cliente " + i + " conectado.");
      
      DataInputStream in = new DataInputStream(clienteConectado.getInputStream());
      DataOutputStream out = new DataOutputStream(clienteConectado.getOutputStream());

      System.out.println("Puerto remoto: " + clienteConectado.getInetAddress().getHostAddress() + ":" + clienteConectado.getPort());
      System.out.println("Puerto local: " + clienteConectado.getLocalAddress().getHostAddress() + ":" + clienteConectado.getLocalPort());

      String mensajeCliente = in.readUTF();
      out.writeUTF("Saludos al cliente del servidor");

      System.out.println("Recibiendo del CLIENTE: \n\t" + mensajeCliente);

      in.close();
      out.close();
      clienteConectado.close();
    }

    servidor.close();
  }
}
