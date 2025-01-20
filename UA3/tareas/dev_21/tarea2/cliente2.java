import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class cliente2 {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 6000;

        System.out.println("Lanzando mensaje al servidor....");
        Socket Cliente = new Socket(Host, Puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF("Soy el cliente 2 y mi puerto local es: "+ Cliente.getLocalPort() +" y mi puerto remoto: "+ Cliente.getPort());

        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("Información del servidor: \n" + flujoEntrada.readUTF());

        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
