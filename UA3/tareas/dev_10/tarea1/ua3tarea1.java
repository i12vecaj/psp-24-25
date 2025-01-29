package tarea1;

import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class ua3tarea1 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    String urlString = "";

    //  pedir URLs/IPs hasta "localhost"
    while (!urlString.equalsIgnoreCase("localhost")) {
      System.out.print("Introduce una URL o IP (escribe 'localhost' para salir): ");
      urlString = scanner.nextLine();

      if (urlString.equalsIgnoreCase("localhost")) {
        System.out.println("¡Hasta la proxima!");
        break;
      }

      try {
        // Convertir la cadena a URI
        URI uri = new URI(urlString);
        // Convertir el URI a un objeto URL
        URL url = uri.toURL();

        // Mostrar información URL/IP
        System.out.println("Información de la URL/IP");
        System.out.println("Host: " + url.getHost());
        System.out.println("Protocolo: " + url.getProtocol());


        if (url.getPort() == -1) {
          System.out.println("Puerto: (predeterminado)");
        } else {
          System.out.println("Puerto: " + url.getPort());
        }
        System.out.println("Ruta: " + url.getPath());
      } catch (Exception error) {
        System.out.println("error: " + error.getMessage());
      }
    }
    scanner.close();
  }
}
