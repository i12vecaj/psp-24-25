import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidores {

    public static void main(String[] arg) throws IOException {
        int Puerto = 6000;// Puerto
        ServerSocket Servidor = new ServerSocket(Puerto);
        System.out.println("Escuchando en " + Servidor.getLocalPort());

        Socket cliente1 = Servidor.accept();
        // Realizar Acciones con cliente 1

        Socket cliente2 = Servidor.accept();
        // Realizar Acciones con cliente 2

        Servidor.close();
    }
}
