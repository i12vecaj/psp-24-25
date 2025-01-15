import java.io.*;
import java.net.*;

public class ua3tarea2Cliente {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int puerto = 6000;

        System.out.println("Iniciando el programa cliente");

        Socket socketCliente1 = new Socket(host, puerto);
        Socket socketCliente2 = new Socket(host, puerto);

        DataOutputStream salidaCliente1 = new DataOutputStream(socketCliente1.getOutputStream());
        System.out.println("Los puertos locales de los clientes son: " + socketCliente1.getLocalPort());
        System.out.println("Los puertos remotos de los clientes son: " + socketCliente1.getPort());
        System.out.println("La dirección IP de la máquina remota es: " + socketCliente1.getInetAddress());

        DataOutputStream salidaCliente2 = new DataOutputStream(socketCliente2.getOutputStream());
        System.out.println("Los puertos locales de los clientes son: " + socketCliente2.getLocalPort());
        System.out.println("Los puertos remotos de los clientes son: " + socketCliente2.getPort());
        System.out.println("La dirección IP de la máquina remota es: " + socketCliente2.getInetAddress());

        DataInputStream entradaCliente1 = new DataInputStream(socketCliente1.getInputStream());
        System.out.println("Saliendo cliente 1: " + entradaCliente1.readUTF());
        
        DataInputStream entradaCliente2 = new DataInputStream(socketCliente2.getInputStream());
        System.out.println("Saliendo cliente 2: " + entradaCliente2.readUTF());

        socketCliente1.close();
        socketCliente2.close();
        salidaCliente1.close();
        salidaCliente2.close();
        entradaCliente1.close();
        entradaCliente2.close();
    }
}
