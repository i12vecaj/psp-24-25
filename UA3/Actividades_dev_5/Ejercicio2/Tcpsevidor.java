import java.io.*;
import java.net.*;

public class Tcpsevidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6000; // Puerto
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor escuchando en el puerto " + servidor.getLocalPort());

        // Acepta conexión de cliente 1
        System.out.println("Esperando cliente 1...");
        Socket cliente1 = servidor.accept();
        System.out.println("Cliente 1 conectado:");
        System.out.println("Puerto Local: " + cliente1.getLocalPort());
        System.out.println("Puerto Remoto: " + cliente1.getPort());

        // Acepta conexión de cliente 2
        System.out.println("Esperando cliente 2...");
        Socket cliente2 = servidor.accept();
        System.out.println("Cliente 2 conectado:");
        System.out.println("Puerto Local: " + cliente2.getLocalPort());
        System.out.println("Puerto Remoto: " + cliente2.getPort());

        // Cerrar sockets
        cliente1.close();
        cliente2.close();
        servidor.close();
        System.out.println("Conexiones cerradas.");
    }
}

