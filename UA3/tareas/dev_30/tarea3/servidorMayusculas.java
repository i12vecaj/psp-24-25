package tarea3;
import java.net.*;
import java.io.*;
public class servidorMayusculas {
    public static void main(String[] args) throws IOException {

        int puerto = 5000;

        System.out.println("Esperando Mensaje del cliente");

        ServerSocket servidor = new ServerSocket(puerto);

        Socket clienteConectado = null;

        clienteConectado = servidor.accept();

        InputStream entrada = null;

        entrada = clienteConectado.getInputStream();


        DataInputStream flujoEntrada = new DataInputStream(entrada);
        //System.out.println(flujoEntrada.readUTF().toUpperCase());
        DataInputStream flujoEntradaMinuscila = new DataInputStream(entrada);

        String datosMayuscula = flujoEntrada.readUTF().toUpperCase();

        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();

        DataOutputStream flujoSalida = new DataOutputStream(salida);

        flujoSalida.writeUTF(datosMayuscula);

        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();



    }
}
