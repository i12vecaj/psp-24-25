import java.net.InetAddress;
import java.util.Scanner;

public class URLInfoViewer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Introduce una URL o IP .Siquiere salir introduzca = localhost");

        while (true) {
            System.out.print("Ingrese una URL o IP: ");
            input = scanner.nextLine().trim();

            if ("localhost".equalsIgnoreCase(input)) {
                System.out.println("Saliendo del programa.");
                break;
            }

            try {

                InetAddress address = InetAddress.getByName(input);
                System.out.println("\nInformación sobre: " + input);
                System.out.println("Nombre del host: " + address.getHostName());
                System.out.println("Dirección IP: " + address.getHostAddress());
            } catch (Exception e) {
                System.out.println("Error: No se pudo procesar la entrada. Verifique si es una URL o dirección IP válida.");
            }

            System.out.println();
        }

        scanner.close();
    }
}
