import java.io.*;
import java.net.*;

public class ua3tarea2fr2 {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 6000;

        System.out.println("se esta iniciando el cliente...");


        Socket cliente = new Socket(Host, Puerto);


        InetAddress ipServidor = cliente.getInetAddress();
        int puertoRemoto = cliente.getPort();
        int puertoLocal = cliente.getLocalPort();

        System.out.println("se esta conectado al servidor:");
        System.out.println("\tip remoto " + ipServidor.getHostAddress());
        System.out.println("\tel puerto remoto: " + puertoRemoto);
        System.out.println("\tel puerto local: " + puertoLocal);


        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        System.out.println("mensaje del servidor: " + flujoEntrada.readUTF());


        flujoEntrada.close();
        cliente.close();

        System.out.println("cliente finalizado.");
    }
}

