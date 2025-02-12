import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 10000;

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             OutputStream output = socket.getOutputStream();
             InputStream input = socket.getInputStream();
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe un mensaje:");

            while (true) {
                System.out.print("> ");
                String mensaje = sc.nextLine();
                output.write(mensaje.getBytes());

                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);
                String respuesta = new String(buffer, 0, bytesRead);
                System.out.println("Servidor: " + respuesta);

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Desconectando...");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
