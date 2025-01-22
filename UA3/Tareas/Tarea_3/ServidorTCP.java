import java.io.*;
import java.net.*;

public class ServidorTCP {
  public static void main(String[] args) throws IOException {
    int puerto = 5000;
    String host = "localhost";
    ServerSocket servidor = new ServerSocket(puerto);

    System.out.println("Esperando que el cliente introduzca una frase para pasarla a mayuscula...");

    Socket clienteConectado = servidor.accept();
    DataInputStream in = new DataInputStream(clienteConectado.getInputStream());
    DataOutputStream out = new DataOutputStream(clienteConectado.getOutputStream());
    String frase = in.readUTF();

    System.out.println("Cliente conectado, transformando "+frase+" a mayuscula...");

    out.writeUTF(frase.toUpperCase());

    System.out.println("Frase Transformada con exito.");
    in.close();
    out.close();
    clienteConectado.close();
  }
}