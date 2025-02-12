import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //Creamos el socket multicast
        MulticastSocket multiSocket = new MulticastSocket();
        //Creamos las variable del puerto y de la direccion IP
        int Puerto = 6000;
        InetAddress grupo = InetAddress.getByName("225.1.1.1");
        String cadena="";
        while(!cadena.trim().equals("*")) {
            System.out.print("Datos a enviar al grupo: ");
            cadena = in.readLine();
            //Creamos el paquete con la cadena y lo enviamos al grupo
            DatagramPacket paquete = new DatagramPacket
                    (cadena.getBytes(), cadena.length(), grupo, Puerto);
            multiSocket.send (paquete);
        }
        //Cerramos el socket
        multiSocket.close ();
        System.out.println ("Socket cerrado...");
    }
}
