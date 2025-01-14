import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Introduce una URL o IP (escribe 'localhost' para salir):");

        while (true) {
            System.out.print("Entrada: ");
            input = scanner.nextLine();
            
            if ("localhost".equalsIgnoreCase(input)) {
                System.out.println("Finalizando programa.");
                break;
            }

            try {
                InetAddress address = InetAddress.getByName(input);
                System.out.println("Información de la URL/IP:");
                System.out.println("  Hostname: " + address.getHostName());
                System.out.println("  Dirección IP: " + address.getHostAddress());
                System.out.println("  Es alcanzable: " + (address.isReachable(5000) ? "Sí" : "No"));
            } catch (UnknownHostException e) {
                System.out.println("Error: No se pudo resolver la URL/IP proporcionada.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }
}
