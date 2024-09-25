import java.io.*;
import java.net.*;

public class ua3tarea2fr2{
    public static void main(String[] args) throws IOException{
        String Host = "localhost";
        int Puerto = 6000;

        Socket Cliente = new Socket(Host, Puerto);
        InetAddress i = Cliente.getInetAddress();
        System.out.println("Puerto local: " + Cliente.getLocalPort());
        System.out.println("Puerto remoto: " + Cliente.getPort());
        System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());

        Cliente.close();
    }
}
