import java.io.*;
import java.net.*;

public class Cliente {
  public static void main(String[] args) throws Exception {
    String host = "localhost";
    int puerto = 5045;

    System.out.println("CLIENTE-1 ENCENDIDO");
    Socket cliente = new Socket(host, puerto);

    System.out.println("Puerto local del cliente: " + cliente.getLocalPort());
    System.out.println("Puerto remoto del servidor: " + cliente.getPort());
    System.out.println("Dirección IP remota del servidor: " + cliente.getInetAddress());

    DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
    flujoSalida.writeUTF("Soy el cliente y me he conectado al servidor");

    DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
    System.out.println("Recibiendo datos del servidor: \n\t" + flujoEntrada.readUTF());

    flujoEntrada.close();
    flujoSalida.close();
    cliente.close();
  }
}