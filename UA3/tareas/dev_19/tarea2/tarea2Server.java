import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int serverPort = 12345;

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Servidor iniciado en el puerto " + serverPort);

            for (int i = 1; i <= 2; i++) {
                System.out.println("Esperando al cliente " + i + "...");
                Socket clientSocket = serverSocket.accept();

                InetSocketAddress remoteAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
                InetSocketAddress localAddress = (InetSocketAddress) clientSocket.getLocalSocketAddress();

                System.out.println("Cliente " + i + " conectado:");
                System.out.println("  Puerto local: " + localAddress.getPort());
                System.out.println("  Puerto remoto: " + remoteAddress.getPort());
                System.out.println("  Dirección IP remota: " + remoteAddress.getAddress().getHostAddress());

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
