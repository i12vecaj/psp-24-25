// Servidor TCP
import java.io.*;
import java.net.*;

public class ua3tarea2fr1 {
    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6000; // Puerto del servidor
        ServerSocket servidor = new ServerSocket(numeroPuerto);

        System.out.println("se esta iniciando el servido y esperando clientes");

        for (int i = 1; i <= 2; i++) {

            Socket clienteConectado = servidor.accept();


            InetAddress ipCliente = clienteConectado.getInetAddress();
            int puertoRemoto = clienteConectado.getPort();
            int puertoLocal = clienteConectado.getLocalPort();

            System.out.println("cliente " + i + " esta conectado:");
            System.out.println("\testa la ip remota: " + ipCliente.getHostAddress());
            System.out.println("\tel puerto remoto: " + puertoRemoto);
            System.out.println("\tel puerto local: " + puertoLocal);


            DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
            flujoSalida.writeUTF(" saludos cliente " + i + ", su conexion esta establecida.");


            flujoSalida.close();
            clienteConectado.close();
        }

        servidor.close();
        System.out.println("el servidor termino.");
    }
}

