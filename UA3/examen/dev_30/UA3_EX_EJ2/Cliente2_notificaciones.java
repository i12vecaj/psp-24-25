package UA3_EX_EJ2;
import java.io.*;
import java.net.*;

public class Cliente2_notificaciones {
    public static void main(String args[]) throws Exception {

        int Puerto = 10008;
        MulticastSocket ms = new MulticastSocket(Puerto);

        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        ms.joinGroup (grupo);

        String mensaje="";

        //
        while(true) {
            byte[] buf = new byte[1000];

            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);

            mensaje = new String(paquete.getData());
            System.out.println ("Aviso del servidor recibido con exito en el cliente 2: " + mensaje.trim());
        }

    }
}