import java.io.*;
 import java.net.*;

 public class ua3tarea2fr1{
    public static void main(String[] args) throws IOException{
        int Puerto = 6000;

        ServerSocket Servidor = new ServerSocket(Puerto);
        Socket cliente1 = null;
        Socket cliente2 = null;
        System.out.println("Esperando cliente...");

        cliente1 = Servidor.accept();
        System.out.println("Cliente 1 conectado");
        System.out.println("Puerto local: " + cliente1.getLocalPort());
        System.out.println("Puerto remoto: " + cliente1.getLocalPort());

        cliente2 = Servidor.accept();
        System.out.println("Cliente 2 conectado");
        System.out.println("Puerto local: " + cliente2.getLocalPort());
        System.out.println("Puerto remoto: " + cliente2.getPort());

        cliente1.close();
        cliente2.close();
        Servidor.close();
    }
 }
