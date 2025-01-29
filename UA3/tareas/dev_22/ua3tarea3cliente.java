import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.print("Inserte un mensaje: ");
            String mensaje = scanner.nextLine();
            output.println(mensaje);

            String respuesta = input.readLine();
            System.out.println("Servidor: " + respuesta);
        } catch (UnknownHostException e) {
            System.out.println("El servidor no ha sido encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());

        }
    }
}
