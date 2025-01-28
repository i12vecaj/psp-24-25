package tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class clienteMinusculas {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 5000;
        String mensajeOriginal = "Hola!! esto es un texto en minuscula";

        System.out.println("Estamos en el cliente......");
        Socket Cliente = new Socket(Host, Puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        flujoSalida.writeUTF(mensajeOriginal);

        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        System.out.println("El texto original es: "+mensajeOriginal+" y el texto en mayusculas es: "+flujoEntrada.readUTF());

        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
