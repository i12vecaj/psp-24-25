import java.io.*;
import java.net.*;
public class Servidor
{
    public static void main(String[] args) throws IOException
    {
        int port = 7000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor activo y escuchando en el puerto " + port);
        Socket client1 = serverSocket.accept();
        System.out.println("Cliente 1 conectado desde " + client1.getInetAddress() + ":" + client1.getPort());
        Socket client2 = serverSocket.accept();
        System.out.println("Cliente 2 conectado desde " + client2.getInetAddress() + ":" + client2.getPort());
        serverSocket.close();
        System.out.println("Servidor cerrado.");
    }
}