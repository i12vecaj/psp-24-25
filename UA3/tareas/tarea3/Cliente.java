import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Escribe un mensaje: ");
            String mensaje = scanner.nextLine();
            salida.println(mensaje);

            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
