import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class leerURL {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Escriba una URL o una IP (escriba 'localhost' para salir):");

        while (true) {
            input = scanner.nextLine().trim();

            if ("localhost".equalsIgnoreCase(input)) {
                System.out.println("Estamos saliendo del programa es pere...");
                break;
            }

            try {
                InetAddress address = InetAddress.getByName(input);
                System.out.println("Información sobre: " + input);
                System.out.println("Nombre del host: " + address.getHostName());
                System.out.println("Dirección IP: " + address.getHostAddress());
                System.out.println("¿Es alcanzable?: " + (address.isReachable(5000) ? "Sí" : "No"));
            } catch (UnknownHostException e) {
                System.out.println("No se pudo resolver el host: " + input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }
}