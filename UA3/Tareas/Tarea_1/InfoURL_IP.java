import java.net.*;
import java.util.Scanner;

public class InfoURL_IP {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input;

    while(true){
      System.out.println("Introduzca la entrada: ");
      input = scanner.nextLine().trim();
      System.out.println("\n------------------------------\n");

      if ("localhost".equalsIgnoreCase(input)){
        System.out.println("\nSaliendo...");
        break;
      }
      try {
        InetAddress IP = InetAddress.getByName(input);

        System.out.println("Entrada: "+input);
        System.out.println("\nNombre del host: "+IP.getHostName());
        System.out.println("\nNombre Canonico del host: "+IP.getCanonicalHostName());
        System.out.println("\nDireccion IP del host: "+IP.getHostAddress());
        System.out.println("\n------------------------------\n");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}