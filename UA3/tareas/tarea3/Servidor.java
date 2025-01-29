import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexión en el puerto " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);
                String respuesta = mensaje.toUpperCase();
                salida.println(respuesta);

                socket.close();
                System.out.println("Cliente desconectado\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
