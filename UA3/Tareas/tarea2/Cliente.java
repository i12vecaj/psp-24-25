import java.io.*;
import java.net.*;
public class Cliente
{
    public static void main(String[] args) throws IOException
    {
        String host = "localhost";
        int port = 7000;
        Socket clientSocket = new Socket(host, port);
        InetAddress remoteAddress = clientSocket.getInetAddress();
        System.out.println("Conectado al servidor: " + host + ":" + port);
        System.out.println("Puerto local: " + clientSocket.getLocalPort());
        System.out.println("Puerto remoto: " + clientSocket.getPort());
        System.out.println("Dirección IP del servidor: " + remoteAddress.getHostAddress());
        System.out.println("Nombre del host remoto: " + remoteAddress.getHostName());
        clientSocket.close();         System.out.println("Conexión cerrada.");
    }
}
