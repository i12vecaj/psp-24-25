import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        System.out.println("Hola, ingresa una URL o IP. Escribe 'localhost' para salir del programa.\n");

        do {
            // Solicita al usuario que ingrese una URL o IP
            System.out.print("Ingresa una URL o IP: ");
            input = reader.readLine();

            // Si la entrada no es 'localhost', intenta resolver la dirección
            if (!input.equalsIgnoreCase("localhost")) {
                try {
                    // Obtiene la dirección IP o el nombre del host a partir de la entrada
                    InetAddress address = InetAddress.getByName(input);
                    // Muestra los detalles generales de la dirección
                    mostrarDetallesGenerales(address, input);
                } catch (UnknownHostException e) {
                    // Captura la excepción UnknownHostException y muestra un mensaje de error
                    System.out.println("La entrada no es válida. Inténtalo de nuevo.");
                }
            } else {
                // Si la entrada es 'localhost', muestra un mensaje y sale del bucle
                System.out.println("Introducido 'localhost', saliendo...");
            }

        // Continúa el bucle mientras la entrada no sea 'localhost'
        } while (!input.equalsIgnoreCase("localhost"));
    }

    // Método que muestra los detalles generales de una URL o IP
    private static void mostrarDetallesGenerales(InetAddress address, String input) {
        // Muestra el nombre del host y la dirección IP
        System.out.println("\nDetalles de la entrada:");
        System.out.println("Host: " + address.getHostName());
        System.out.println("Dirección IP: " + address.getHostAddress());

        // Si la entrada es una URL, muestra detalles adicionales
        if (esURL(input)) {
            try {
                // Crea un objeto URL y muestra sus detalles
                URL url = new URL(!input.startsWith("http://") && !input.startsWith("https://") ? "http://" + input : input);
                System.out.println("URL completa: " + url.toString());
                System.out.println("Protocolo: " + url.getProtocol());
                System.out.println("Autoridad: " + url.getAuthority());
                System.out.println("Puerto: " + (url.getPort() == -1 ? "Por defecto" : url.getPort()));
                System.out.println("Puerto por defecto: " + url.getDefaultPort());
            } catch (MalformedURLException e) {
                System.out.println("La URL es inválida.");
            }
        }
        System.out.println("==================================================\n");
    }

    // Método que verifica si la entrada es una URL válida
    private static boolean esURL(String input) {
        // Verifica si la entrada contiene un punto, lo que indica que es una URL
        return input.contains(".");
    }
}