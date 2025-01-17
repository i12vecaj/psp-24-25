import java.io.*;
import java.net.*;
public class Cliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;//puerto remoto

        System.out.println("Programa cliente iniciado....\n");

        Socket cliente1 = new Socket(host,puerto);
        Socket cliente2 = new Socket(host,puerto);

        DataOutputStream salidaCliente1 = new DataOutputStream(cliente1.getOutputStream());

        System.out.println("Los puertos locales del cliente 1 son: " + cliente1.getLocalPort());
        System.out.println("Los puertos remotos del cliente 1 son: " + cliente1.getPort());
        System.out.println("La dirección IP de la máquina remota del cliente 1 es: " + cliente1.getInetAddress()+" \n");

        DataOutputStream salidaCliente2 = new DataOutputStream(cliente2.getOutputStream());

        System.out.println("Los puertos locales del cliente 2 son: " + cliente2.getLocalPort());
        System.out.println("Los puertos remotos del cliente 2 son: " + cliente2.getPort());
        System.out.println("La dirección IP de la máquina remota del cliente 2 es: " + cliente2.getInetAddress()+" \n");


        DataInputStream entradaCliente1 = new DataInputStream(cliente1.getInputStream());
        System.out.println("Cliente 1 "+ entradaCliente1.readUTF());

        DataInputStream entradaCliente2 = new DataInputStream(cliente2.getInputStream());
        System.out.println("Cliente 2 "+ entradaCliente2.readUTF());

        cliente1.close();
        cliente2.close();

        salidaCliente1.close();
        salidaCliente2.close();

        entradaCliente1.close();
        entradaCliente2.close();
    }
}
