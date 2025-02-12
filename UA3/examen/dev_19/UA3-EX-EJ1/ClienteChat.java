import java.io.*;
import java.net.*;

public class ClienteChat {
    private static  String servidor = "127.0.0.1";
    private static int puerto = 10000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(servidor, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(entrada.readLine());  // Mensaje del servidor ("Ingresa tu nombre:")
            String nombre = teclado.readLine();
            salida.println(nombre);

            // Hilo para recibir mensajes
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado del servidor.");
                }
            }).start();

            // Leer y enviar mensajes
            String mensaje;
            while (true) {
                mensaje = teclado.readLine();
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    salida.println(mensaje);
                    break;
                }
                salida.println(mensaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

