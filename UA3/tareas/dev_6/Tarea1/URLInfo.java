import java.net.*; // Importamos las clases necesarias para trabajar con direcciones IP
import java.util.*; // Importamos Scanner para leer datos del usuario

public class URLInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario
        String url; // Variable para almacenar la URL o IP ingresada

        System.out.println("Introduce una URL o IP (escribe 'localhost' para salir):");

        while (true) { // Bucle infinito para seguir pidiendo datos hasta que el usuario escriba "localhost"
            System.out.print(--> ");
            url = sc.nextLine(); // Leemos la entrada del usuario

            if (url.equals("localhost")) { // Si el usuario escribe "localhost", salimos del programa
                System.out.println("Saliendo...");
                break;
            }

            try {
                InetAddress info = InetAddress.getByName(url); // Obtenemos información de la URL/IP ingresada
                System.out.println("----------------------");
                System.out.println("Host: " + info.getHostName()); // Mostramos el nombre del host
                System.out.println("IP: " + info.getHostAddress()); // Mostramos la dirección IP
                System.out.println("Es alcanzable? " + info.isReachable(3000)); // Verificamos si la dirección es alcanzable en 3 segundos
                System.out.println("----------------------\n");
            } catch (Exception e) { // Capturamos cualquier error que ocurra
                System.out.println("Error: URL o IP no válida."); // Mostramos un mensaje de error si la entrada no es válida
            }
        }

        sc.close(); // Cerramos el Scanner para liberar recursos
    }
}
