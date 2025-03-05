import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR_IP = "127.0.0.1";
        final int PUERTO = 10045;
        
        try (Socket socket = new Socket(SERVIDOR_IP, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.print("Ingrese un mensaje: ");
            String mensaje = scanner.nextLine();
            
            salida.println(mensaje);
            
            String respuesta = entrada.readLine();
            System.out.println("Mensaje modificado: " + respuesta);
            
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}