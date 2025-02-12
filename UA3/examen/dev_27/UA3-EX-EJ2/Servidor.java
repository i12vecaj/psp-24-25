import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        MulticastSocket ms = new MulticastSocket();
        int Puerto = 12345;
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        String cadena="";
        System.out.println("Cuando introduzcas *, se cerrara");
        while(!cadena.trim().equals("*")) {
            System.out.print("Datos a enviar al grupo: ");
            cadena = in.readLine();
            DatagramPacket paquete = new DatagramPacket
                    (cadena.getBytes(), cadena.length(), grupo, Puerto);
            ms.send (paquete);
        }
        ms.close ();
        System.out.println ("Socket cerrado...");
    }
}
