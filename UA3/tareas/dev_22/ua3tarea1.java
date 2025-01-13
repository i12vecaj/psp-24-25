import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ua3tarea1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada;

        do {
            System.out.print("Ingrese una IP o URL (o 'localhost' para salir): ");
            entrada = scanner.nextLine();

            try {
                InetAddress address = InetAddress.getByName(entrada);
                System.out.println("Nombre de host: " + address.getHostName());
                System.out.println("Dirección IP: " + address.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println("No se pudo resolver la dirección: " + entrada);
            }
        } while ("localhost".equalsIgnoreCase(entrada));
            System.out.println("Saliendo del programa....");
            scanner.close();
    }
}