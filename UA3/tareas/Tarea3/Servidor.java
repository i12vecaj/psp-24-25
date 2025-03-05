import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 10045;
        
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones...");
            
            while (true) {
                try (Socket socket = servidor.accept();
                     BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(
                         socket.getOutputStream(), true)) {
                    
                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    
                    String mensaje = entrada.readLine();
                    String respuesta = mensaje.toUpperCase();
                    
                    salida.println(respuesta);
                    
                } catch (IOException e) {
                    System.err.println("Error en la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}