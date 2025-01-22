package tarea_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, Puerto);

        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un mensaje para enviar al servidor: ");
        String mensaje = scanner.nextLine();

        flujoSalida.writeUTF(mensaje);

        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        String respuestaServidor = flujoEntrada.readUTF();
        System.out.println("Recibiendo del SERVIDOR: \n\t" + respuestaServidor);

        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
        scanner.close();
    }
}
