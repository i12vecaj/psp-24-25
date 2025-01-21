import java.io.*;
import java.net.*;

public class Cliente2 {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000; // Puerto

        Socket cliente = new Socket(host, puerto);
        InetAddress i = cliente.getInetAddress();
        System.out.println("Cliente 2 conectado:");
        System.out.println("Puerto Local: " + cliente.getLocalPort());
        System.out.println("Puerto Remoto: " + cliente.getPort());
        System.out.println("Dirección IP Host Remoto: " + i.getHostAddress());
        System.out.println("Nombre Host/IP Remoto: " + i.getHostName());

        cliente.close();
    }
}
