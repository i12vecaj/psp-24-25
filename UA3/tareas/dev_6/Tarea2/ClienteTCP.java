import java.io.IOException;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        final String SERVIDOR = "127.0.0.1"; // Dirección IP del servidor (localhost)
        final int PUERTO = 5000; // Puerto del servidor

        try {
            Socket socket = new Socket(SERVIDOR, PUERTO);
            System.out.println("Conectado al servidor");

            System.out.println("Puerto local del cliente: " + socket.getLocalPort());
            System.out.println("Puerto remoto del servidor: " + socket.getPort());
            System.out.println("Dirección IP del servidor: " + socket.getInetAddress().getHostAddress());

            socket.close();
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
