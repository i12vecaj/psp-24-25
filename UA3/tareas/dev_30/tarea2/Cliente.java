package ClienteServidor;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws Exception {

        String Host = "localhost";
        int Puerto = 5800;

        System.out.println("Estamos en el cliente1......");
        Socket Cliente = new Socket(Host, Puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF("Hola, servidor desde el cliente 1");

        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("Hola! soy el cliente 1 y mi puerto es: "+Cliente.getPort() +" y mi IP es: "+Cliente.getInetAddress().getHostAddress() +"\n\t" + flujoEntrada.readUTF());

        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}