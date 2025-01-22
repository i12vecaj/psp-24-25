import java.net.*;
import java.util.Scanner;

public class HostInfoRetriever {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Ingrese el dato: ");
            userInput = inputScanner.nextLine().trim();
            System.out.println("\n******************************\n");

            if ("salir".equalsIgnoreCase(userInput)) {
                System.out.println("\nFinalizando...");
                break;
            }

            try {
                InetAddress hostAddress = InetAddress.getByName(userInput);

                System.out.println("Dato ingresado: " + userInput);
                System.out.println("\nNombre del servidor: " + hostAddress.getHostName());
                System.out.println("\nNombre Canónico del servidor: " + hostAddress.getCanonicalHostName());
                System.out.println("\nDirección IP del servidor: " + hostAddress.getHostAddress());
                System.out.println("\n******************************\n");
            } catch (Exception error) {
                throw new RuntimeException(error);
            }
        }
    }
}
