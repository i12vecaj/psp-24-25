import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String direccionServidor = "localhost"; // Dirección del servidor
        int puerto = 5000; // Puerto 5000

        try (Socket socket = new Socket(direccionServidor, puerto)) {
            // Para enviar mensaje al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Para recibir respuesta del servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Pedir mensaje al usuario
            BufferedReader lectorUsuario = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce un mensaje: ");
            String mensaje = lectorUsuario.readLine();

            // Enviar mensaje al servidor
            salida.println(mensaje);

            // Recibir respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Mensaje recibido del servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}

