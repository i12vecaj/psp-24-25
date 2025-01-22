import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    int puerto = 5000;
    String host = "localhost";
    System.out.println("Introduzca una frase...");

    String frase = scanner.nextLine();
    Socket cliente = new Socket(host,puerto);

    DataInputStream in = new DataInputStream(cliente.getInputStream());
    DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
    out.writeUTF(frase);
    String fraseMayuscula = in.readUTF();
    System.out.println("El servidor ha convertido mi frase en mayuscula: "+fraseMayuscula);

    scanner.close();
    in.close();
    out.close();
    cliente.close();
  }
}
