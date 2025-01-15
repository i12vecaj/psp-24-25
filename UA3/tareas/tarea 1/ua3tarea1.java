import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class ua3tarea1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String introducirIP;

        while (true) {
            System.out.println("Por favor, ingrese un nombre o dirección: ");
            introducirIP = scanner.nextLine().trim();

            if ("localhost".equalsIgnoreCase(introducirIP)) {
                System.out.println("\nFinalizando el programa");
                break;
            }
            try {
                InetAddress direccion = InetAddress.getByName(introducirIP);

                System.out.println("Entrada proporcionada: " + introducirIP);
                System.out.println("\nNombre del host: " + direccion.getHostName());
                System.out.println("\nLa dirección es: " + Arrays.toString(direccion.getAddress()));
                System.out.println("\nDirección IP: " + direccion.getHostAddress());
            } catch (Exception e) {
                throw new RuntimeException("Se produjo un error al procesar la entrada.", e);
            }
        }
    }
}