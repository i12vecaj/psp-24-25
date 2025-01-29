import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("El servidor esta leyendo en el puerto 12345");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    String mensaje = input.readLine();
                    String mensajeMayus = mensaje.toUpperCase();
                    output.println(mensajeMayus);
                } catch (IOException e) {
                    System.out.println("Excepcion del servidor: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer a traves del puerto 12345");
            e.printStackTrace();
        }
    }
}
