package UA3_EX_EJ1;
import java.io.*;
import java.net.*;

public class Cliente_2_chat {

    //En mi caso prefiero utilizar UDP ya que siendo un chatbox donde solo se van a enviar mensajes, lo que queremos que los mensajes se envien de manera rapida.


    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int Puerto = 10009;
        MulticastSocket ms = new MulticastSocket(Puerto);

        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        ms.joinGroup (grupo);

        String mensaje="";
        String cadenaEnvio="";

        //
        while(!mensaje.trim().equals("SALIR")) {

            System.out.print("¿Que quiere enviar?: ");
            cadenaEnvio = in.readLine();
            DatagramPacket paqueteEnvio = new DatagramPacket (cadenaEnvio.getBytes(), cadenaEnvio.length(), grupo, Puerto);
            ms.send (paqueteEnvio);


            byte[] buf = new byte[1000];

            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);

            mensaje = new String(paquete.getData());
            System.out.println ("Aviso del servidor recibido por el cliente 1: " + mensaje.trim());
        }
    }
}