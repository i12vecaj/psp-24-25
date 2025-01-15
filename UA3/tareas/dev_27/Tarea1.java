import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InetAddress dir = null;

        System.out.println("Introduce una URL o IP (escribe 'localhost' para salir):");
        while (true) {
            String input = scanner.nextLine();

            // Salir del programa si el usuario escribe "localhost"
            if (input.equalsIgnoreCase("localhost")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            try {
                dir = InetAddress.getByName(input);
                System.out.println("\tDirección proporcionada: " + input);
                System.out.println("\tNombre de host: " + dir.getHostName());
                System.out.println("\tDirección IP: " + dir.getHostAddress());

                // Obtener todas las direcciones asociadas al host
                InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
                System.out.println("\tOtras direcciones asociadas:");
                for (InetAddress direccion : direcciones) {
                    System.out.println("\t\t" + direccion.toString());
                }
            } catch (Exception e) {
                System.out.println("\tError al procesar la URL o IP: " + e.getMessage());
            }

            System.out.println("\nIntroduce otra URL o IP (escribe 'localhost' para salir):");
        }

        scanner.close();
    }
}