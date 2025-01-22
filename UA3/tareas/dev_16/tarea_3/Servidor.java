import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor en espera de conexiones...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Cliente conectado.");

                    String mensaje = entrada.readLine();
                    System.out.println("Mensaje recibido: " + mensaje);

                    String respuesta = mensaje.toUpperCase();
                    salida.println(respuesta);
                } catch (IOException e) {
                    System.err.println("Error al manejar la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }
}