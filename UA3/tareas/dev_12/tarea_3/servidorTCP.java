package tarea_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] arg) throws IOException {

        int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        System.out.println("Esperando al cliente.....");
        clienteConectado = servidor.accept();

        DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

        String mensajeCliente = flujoEntrada.readUTF();
        System.out.println("Recibiendo del CLIENTE: \n\t" + mensajeCliente);

        String mensajeConvertido = mensajeCliente.toUpperCase();

        DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

        flujoSalida.writeUTF(mensajeConvertido);
        System.out.println("Enviando al CLIENTE: \n\t" + mensajeConvertido);

        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
