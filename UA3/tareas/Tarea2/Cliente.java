import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String servidorDireccion = "127.0.0.1"; 
        int servidorPuerto = 10045;

        try (Socket socket = new Socket(servidorDireccion, servidorPuerto)) {
           
            int localPuerto = socket.getLocalPort();
            int remotoPuerto = socket.getPort(); 
            String remotaDireccion = socket.getInetAddress().getHostAddress();

            System.out.println("Conectado a " + remotaDireccion + ":" + servidorPuerto); 
            System.out.println("Puerto local: " + localPuerto + ", Puerto remoto: " + remotoPuerto);
        } catch (IOException e) {
            System.out.println("Error al conectar al servidor: " + e.getMessage());
        }
    }
}