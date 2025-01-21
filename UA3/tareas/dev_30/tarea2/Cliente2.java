package ClienteServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Cliente2 {

    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 5800;

        System.out.println("Estamos en el cliente 2......");
        Socket Cliente2 = new Socket(Host, Puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente2.getOutputStream());

        flujoSalida.writeUTF("Hola! servidor desde cliente 2");

        DataInputStream flujoEntrada = new DataInputStream(Cliente2.getInputStream());

        System.out.println("Hola! soy el cliente 2 y mi puerto es: "+Cliente2.getPort() +" y mi IP es: "+Cliente2.getInetAddress().getHostAddress() +"\n\t" + flujoEntrada.readUTF());

        flujoEntrada.close();
        flujoSalida.close();
        Cliente2.close();
    }
}