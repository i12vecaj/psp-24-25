import java.util.Scanner;
import java.net.URL;
import java.net.MalformedURLException;

public class ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce una URL o IP (escribe 'localhost' para salir): ");
            String input = scanner.nextLine().trim();
            
            if (input.equals("localhost")) {
                break;
            }
            
            String urlString = input;
            if (!urlString.matches("^[a-zA-Z]+://.*")) {
                urlString = "http://" + urlString;
            }
            
            try {
                URL url = new URL(urlString);
                mostrarInformacionURL(url);
            } catch (MalformedURLException e ) {
                System.out.println("URL no válida: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Saliendo del programa.");
    }

    private static void mostrarInformacionURL(URL url) {
        System.out.println("Protocolo: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefaultPort();
        }
        System.out.println("Puerto: " + port);
        System.out.println("Autoridad: " + url.getAuthority());
        System.out.println("Ruta: " + url.getPath());
        System.out.println("Consulta: " + url.getQuery());
        System.out.println("Archivo: " + url.getFile());
        System.out.println("Información del usuario: " + url.getUserInfo());
        System.out.println("-----------------------------");
    }
}
