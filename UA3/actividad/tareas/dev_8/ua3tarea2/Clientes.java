import java.io.*;
import java.net.*;

public class Clientes {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 6000;
        Socket Cliente = new Socket(Host, Puerto);
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF("Alo presidentes");

        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("Servidor ->  \n\t" + flujoEntrada.readUTF());

        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}