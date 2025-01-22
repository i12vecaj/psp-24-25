import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Servidor esperando conexiones...");
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());
                    
                    byte[] buffer = new byte[1024];
                    int bytesLeidos = clientSocket.getInputStream().read(buffer);
                    String mensajeRecibido = new String(buffer, 0, bytesLeidos);
                    
                    System.out.println("Mensaje recibido: " + mensajeRecibido);
                    
                    String mensajeEnMayusculas = mensajeRecibido.toUpperCase();
                    clientSocket.getOutputStream().write(mensajeEnMayusculas.getBytes());
                    
                    System.out.println("Mensaje enviado en mayúsculas");

                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
