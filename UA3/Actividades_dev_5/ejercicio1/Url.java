import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InfoIpURL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Información de URL o Dirección IP ===");
        System.out.println("Ingrese una URL o dirección IP para obtener información.");
        System.out.println("Escriba 'salir' para finalizar el programa.");
        System.out.println();

        while (true) {
            System.out.print("Entrada: ");
            String input = scanner.nextLine().trim();

           
            if ("salir".equalsIgnoreCase(input)) {
                System.out.println("Gracias por usar el programa. ¡Adiós!");
                break;
            }

            try {
               
                InetAddress address = InetAddress.getByName(input);

                System.out.println("\n=== Resultados ===");
                System.out.println("Entrada: " + input);
                System.out.println("Nombre del host: " + address.getHostName());
                System.out.println("Dirección IP: " + address.getHostAddress());
                System.out.println("==================\n");
            } catch (UnknownHostException e) {
                System.out.println("Error: No se encontró información para la entrada '" + input + "'. Verifique e intente de nuevo.\n");
            } catch (Exception e) {
                System.out.println("Error inesperado al procesar la entrada: " + input + ". Por favor, inténtelo nuevamente.\n");
            }
        }

        scanner.close();
    }
}
