import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url;
        String ip;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Introduce una IP o URL (o 'localhost' para salir): ");
            url = sc.nextLine();

            if (!"localhost".equalsIgnoreCase(url)) {
                try {
                    InetAddress address = InetAddress.getByName(url);
                    ip = address.getHostAddress();
                    System.out.println("\nNombre del host: " + address.getHostName());
                    System.out.println("Dirección IP: " + ip + "\n");
                } catch (UnknownHostException e) {
                    System.out.println("No se pudo obtener la IP de: " + url);
                }
            }
        } while (!"localhost".equalsIgnoreCase(url));

        System.out.println("Saliendo del programa.");
        sc.close();
    }
}