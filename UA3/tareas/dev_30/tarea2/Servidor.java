package ClienteServidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] arg) throws IOException {

        int contador =1;
        for (int i=0; i<2;i++){

            System.out.println("Esperando a los clientes........");

            int numeroPuerto = 5800;

            ServerSocket servidor = new ServerSocket(numeroPuerto);
            Socket clienteConectado = null;
            // Hacemos que el servidor espere a que un cliente se conecte
            clienteConectado = servidor.accept();

            InputStream entrada = null;

            entrada = clienteConectado.getInputStream();


            DataInputStream flujoEntrada = new DataInputStream(entrada);

            System.out.println(flujoEntrada.readUTF());

            OutputStream salida = null;
            salida = clienteConectado.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            flujoSalida.writeUTF("Saludos al cliente numero: "+contador+" desde el servidor. ");

            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();

            contador++;
        }

    }
}