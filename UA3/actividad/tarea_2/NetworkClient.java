import java.io.*;
import java.net.*;

public class NetworkClient {
    public static void main(String[] args) throws Exception {
        String serverHost = "localhost";
        int serverPort = 4000;

        Socket connection1 = new Socket(serverHost, serverPort);
        Socket connection2 = new Socket(serverHost, serverPort);

        DataInputStream inputStream1 = new DataInputStream(connection1.getInputStream());
        DataInputStream inputStream2 = new DataInputStream(connection2.getInputStream());

        DataOutputStream outputStream1 = new DataOutputStream(connection1.getOutputStream());
        DataOutputStream outputStream2 = new DataOutputStream(connection2.getOutputStream());

        System.out.println("CONEXIONES INICIADAS....");

        System.out.println("******************************");

        System.out.println("Conexión 1 - Dirección local: " + connection1.getLocalAddress().getHostAddress() + ":" + connection1.getLocalPort());
        System.out.println("Conexión 1 - Dirección remota: " + connection1.getInetAddress().getHostAddress() + ":" + connection1.getPort());

        System.out.println("******************************");

        System.out.println("Conexión 2 - Dirección local: " + connection2.getLocalAddress().getHostAddress() + ":" + connection2.getLocalPort());
        System.out.println("Conexión 2 - Dirección remota: " + connection2.getInetAddress().getHostAddress() + ":" + connection2.getPort());

        outputStream1.writeUTF("Hola al SERVIDOR desde la CONEXIÓN 1");
        outputStream2.writeUTF("Hola al SERVIDOR desde la CONEXIÓN 2");

        System.out.println("******************************");
        System.out.println("Respuesta del SERVIDOR para conexión 1: \n\t" + inputStream1.readUTF());
        System.out.println("******************************");
        System.out.println("Respuesta del SERVIDOR para conexión 2: \n\t" + inputStream2.readUTF());
        System.out.println("******************************");

        inputStream1.close();
        inputStream2.close();

        outputStream1.close();
        outputStream2.close();

        connection1.close();
        connection2.close();
    }
}
