import java.io.*;
import java.net.*;

public class Cliente2 {
  public static void main(String[] args) throws Exception {
    String host = "localhost";
    int puerto = 5045;

    System.out.println("CLIENTE-2 ENCENDIDO");
    Socket cliente2 = new Socket(host, puerto);

    System.out.println("Puerto local del cliente 2: " + cliente2.getLocalPort());
    System.out.println("Puerto remoto del servidor: " + cliente2.getPort());
    System.out.println("Dirección IP remota del servidor: " + cliente2.getInetAddress());

    DataOutputStream flujoSalida = new DataOutputStream(cliente2.getOutputStream());
    flujoSalida.writeUTF("Soy el segundo cliente y me he conectado al servidor");
    DataInputStream flujoEntrada = new DataInputStream(cliente2.getInputStream());
    System.out.println("Recibiendo datos del servidor: \n\t" + flujoEntrada.readUTF());

    flujoEntrada.close();
    flujoSalida.close();
    cliente2.close();
  }
}