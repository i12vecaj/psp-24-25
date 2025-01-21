import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 10000; 

        try (Socket clientSocket = new Socket(serverIP, serverPort)) {
            InetSocketAddress remoteAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
            InetSocketAddress localAddress = (InetSocketAddress) clientSocket.getLocalSocketAddress();

            System.out.println("Conectado al servidor:");
            System.out.println("  Puerto local: " + localAddress.getPort());
            System.out.println("  Puerto remoto: " + remoteAddress.getPort());
            System.out.println("  Dirección IP remota: " + remoteAddress.getAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
