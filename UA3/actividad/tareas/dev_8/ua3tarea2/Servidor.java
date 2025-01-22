import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] arg) throws IOException {
        int numeroPuerto = 6000; // Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        System.out.println("Escuchando");
        clienteConectado = servidor.accept();

        InputStream entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        System.out.println("Cliente ->  \n\t" + flujoEntrada.readUTF());

        OutputStream salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        flujoSalida.writeUTF("Alo presidente");

        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}