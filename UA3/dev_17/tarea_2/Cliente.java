import java.io.*;
import java.net.*;
public class Cliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost"; //localhost
        int puerto = 6000;//puerto remoto

        System.out.println("Programa cliente iniciado....\n");

        //Asignamos el puerto y el host a los clientes
        Socket cliente1 = new Socket(host,puerto);
        Socket cliente2 = new Socket(host,puerto);

        //Creamos los flujos de salida del cliente 1
        DataOutputStream salidaCliente1 = new DataOutputStream(cliente1.getOutputStream());

        //Mostramos la informacion
        System.out.println("Los puertos locales del cliente 1 son: " + cliente1.getLocalPort());
        System.out.println("Los puertos remotos del cliente 1 son: " + cliente1.getPort());
        System.out.println("La dirección IP de la máquina remota del cliente 1 es: " + cliente1.getInetAddress()+" \n");

        //Creamos los flujos de salida del cliente 2
        DataOutputStream salidaCliente2 = new DataOutputStream(cliente2.getOutputStream());

        //Mostramos la informacion
        System.out.println("Los puertos locales del cliente 2 son: " + cliente2.getLocalPort());
        System.out.println("Los puertos remotos del cliente 2 son: " + cliente2.getPort());
        System.out.println("La dirección IP de la máquina remota del cliente 2 es: " + cliente2.getInetAddress()+" \n");

        //Creamos el flujo de entrada al servidor del cliennte 1
        DataInputStream entradaCliente1 = new DataInputStream(cliente1.getInputStream());
        System.out.println("Cliente 1 "+ entradaCliente1.readUTF());

        //Creamos el flujo de entrada al servidor del cliennte 1
        DataInputStream entradaCliente2 = new DataInputStream(cliente2.getInputStream());
        System.out.println("Cliente 2 "+ entradaCliente2.readUTF());

        //Cerramos los sockets y flujos
        cliente1.close();
        cliente2.close();

        salidaCliente1.close();
        salidaCliente2.close();

        entradaCliente1.close();
        entradaCliente2.close();
    }
}
