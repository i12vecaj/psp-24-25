
import java.io.*;
import java.net.*;

public class cliente{
    public static void main(String args[]) throws Exception {

        int Puerto = 5000;
        MulticastSocket ms = new MulticastSocket(Puerto);

        InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo


        ms.joinGroup (grupo);

        String msg="";

        //
        while(!msg.trim().equals("*")) {
            byte[] buf = new byte[1000];

            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);

            msg = new String(paquete.getData());
            System.out.println ("Recibo: " + msg.trim());
        }
        ms.leaveGroup (grupo);
        ms.close ();
        System.out.println ("Socket cerrado...");
    }
}
