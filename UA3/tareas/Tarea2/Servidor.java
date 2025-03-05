import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 10045; 
        try (ServerSocket serverSocket = new ServerSocket(puerto)) { 
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            for (int i = 0; i < 2; i++) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente " + (i + 1) + " conectado desde " + clienteSocket.getInetAddress() + ":" + clienteSocket.getPort());

                int localPuerto = clienteSocket.getLocalPort();
                int remotoPuerto = clienteSocket.getPort();
                System.out.println("Cliente " + (i + 1) + " - Puerto local: " + localPuerto + ", Puerto remoto: " + remotoPuerto);
                clienteSocket.close();
            }

            System.out.println("Servidor cerrado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}