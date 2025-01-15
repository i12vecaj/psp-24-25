import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InfoIpURL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Ingrese una URL o dirección IP para obtener informacion (escriba 'localhost' para salir):");

        while (true) {
            System.out.print("Entrada: ");
            input = scanner.nextLine().trim();

            // Salir si el usuario ingresa "localhost"
            if ("localhost".equalsIgnoreCase(input)) {
                System.out.println("Saliendo del programa...");
                break;
            }

            try {
                // Obtener información sobre la URL/IP
                InetAddress address = InetAddress.getByName(input);

                System.out.println("-------------------------------------");
                System.out.println("Información de la entrada: " + input);
                System.out.println("Nombre del host: " + address.getHostName());
                System.out.println("Dirección IP: " + address.getHostAddress());
                System.out.println("-------------------------------------");
            } catch (UnknownHostException e) {
                System.out.println("Error: No se pudo encontrar informacion para la entrada: " + input);
            } catch (Exception e) {
                System.out.println("Error: Ocurrio un problema al procesar la entrada: " + input);
            }
        }

        scanner.close();
    }
}
