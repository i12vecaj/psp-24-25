import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Clientes {
    public static void main(String[] arg) throws IOException {
        String Host = "localhost";
        int Puerto = 6000;// Puerto

        Socket Cliente = new Socket(Host, Puerto);
        InetAddress i = Cliente.getInetAddress();
        System.out.println("\n Cliente 1");
        System.out.println("\n Puerto Local: " + Cliente.getLocalPort());
        System.out.println(" Puerto Remoto: " + Cliente.getPort());
        System.out.println(" Nombre Host/IP: " + Cliente.getInetAddress());
        System.out.println(" Host Remoto: " + i.getHostName().toString());
        System.out.println(" IP Host Remoto: " + i.getHostAddress().toString());

        Cliente.close();

        Socket Cliente2 = new Socket(Host, Puerto);
        InetAddress n = Cliente.getInetAddress();
        System.out.println("\n Cliente 2");
        System.out.println("\n Puerto Local: " + Cliente.getLocalPort());
        System.out.println(" Puerto Remoto: " + Cliente.getPort());
        System.out.println(" Nombre Host/IP: " + Cliente.getInetAddress());
        System.out.println(" Host Remoto: " + i.getHostName().toString());
        System.out.println(" IP Host Remoto: " + i.getHostAddress().toString());
    }

}
