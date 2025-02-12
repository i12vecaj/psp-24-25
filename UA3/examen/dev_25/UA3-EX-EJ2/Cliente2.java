import java.io.*;
import java.net.*;

public class Cliente2 {
    public static void main(String args[]) throws Exception {
        //Creamos una variable con el puerto del servidor.
        int puerto = 6000;
        //Creamos un socket MulticastSocket con el puerto especificado.
        MulticastSocket multiSocket = new MulticastSocket(puerto);

        //Establecemos la dirección del grupo multicast.
        InetAddress grupo = InetAddress.getByName("225.1.1.1");//Grupo
        multiSocket.joinGroup (grupo);
        String msg="";

        //Recibimos y mostramos los mensajes enviados por el otro cliente.
        while(!msg.trim().equals("*")) {
            byte[] buf = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            multiSocket.receive(paquete);

            //Mostramos el mensaje recibido.
            msg = new String(paquete.getData());
            System.out.println ("Recibo: " + msg.trim());
        }

        //Cerramos el socket del grupo;
        multiSocket.close ();
        System.out.println ("Socket cerrado...");
    }
}