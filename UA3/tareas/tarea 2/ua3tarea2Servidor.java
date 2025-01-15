import java.io.*;
import java.net.*;

public class ua3tarea2Servidor {
    public static void main(String[] args) {
        int puerto = 6000;

        try{

            ServerSocket socketServidor = new ServerSocket(puerto);
            System.out.println("El servidor está en escucha...");

            Socket cliente1 = socketServidor.accept();
            Socket cliente2 = socketServidor.accept();

            System.out.println("Puerto local del cliente 1: " + cliente1.getLocalPort());
            System.out.println("Puerto remoto del cliente 1: " + cliente1.getPort());

            System.out.println("Puerto local del cliente 2: " + cliente2.getLocalPort());
            System.out.println("Puerto remoto del cliente 2: " + cliente2.getPort());

            OutputStream salidaCliente1;
            salidaCliente1 = cliente1.getOutputStream();
            DataOutputStream flujoSalidaCliente1 = new DataOutputStream(salidaCliente1);
            flujoSalidaCliente1.writeUTF("Saliendo");

            OutputStream salidaCliente2;
            salidaCliente2 = cliente2.getOutputStream();
            DataOutputStream flujoSalidaCliente2 = new DataOutputStream(salidaCliente2);
            flujoSalidaCliente2.writeUTF("Saliendo");

            socketServidor.close();
            cliente1.close();
            cliente2.close();
            salidaCliente1.close();
            flujoSalidaCliente1.close();
            salidaCliente2.close();
            flujoSalidaCliente2.close();

        }catch(Exception e){
            throw new RuntimeException("Fallo a conectar");
        }

    }
}