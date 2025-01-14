import java.net.*;
import java.util.*;

public class URLInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url;

        System.out.println("Introduce una URL o IP (escribe 'localhost' para salir):");

        while (true) {
            System.out.print("--> ");
            url = sc.nextLine();

            if (url.equals("localhost")) {
                System.out.println("Saliendo...");
                break;
            }

            try {
                InetAddress info = InetAddress.getByName(url);
                System.out.println("----------------------");
                System.out.println("Host: " + info.getHostName());
                System.out.println("IP: " + info.getHostAddress());
                System.out.println("----------------------\n");
            } catch (Exception e) {
                System.out.println("Error: URL o IP no válida.");
            }
        }

        sc.close();
    }
}
