import java.io.*;
import java.net.*;

public class Servidor {
  public static void main(String[] arg) throws IOException {
    int numeroPuerto = 5045;
    ServerSocket servidor = new ServerSocket(numeroPuerto);
    System.out.println("SERVIDOR ENCENDIDO");

    for (int i = 1; i <= 2; i++) {
      Socket clienteConectado = servidor.accept();

      System.out.println("Cliente " + i + " conectado:");
      System.out.println("\tPuerto local del servidor: " + servidor.getLocalPort());
      System.out.println("\tPuerto remoto del cliente: " + clienteConectado.getPort());
      System.out.println("\tDirección IP remota del cliente: " + clienteConectado.getInetAddress());

      InputStream entrada = clienteConectado.getInputStream();
      DataInputStream flujoEntrada = new DataInputStream(entrada);
      System.out.println("Recibiendo datos del cliente " + i + ": \n\t" + flujoEntrada.readUTF());
      OutputStream salida = clienteConectado.getOutputStream();
      DataOutputStream flujoSalida = new DataOutputStream(salida);
      flujoSalida.writeUTF("El cliente " + i + " se ha conectado al servidor");

      flujoEntrada.close();
      flujoSalida.close();
      clienteConectado.close();
    }

    servidor.close();
    System.out.println("Servidor cerrado.");
  }
}